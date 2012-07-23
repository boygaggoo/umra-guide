package com.kdehairy.freelance.umra.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kdehairy.freelance.umra.model.Toc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class Repository extends SQLiteOpenHelper {

	private static final String NAME = "data.sqlite";
	private static final int VERSION = 1;
	private static final String CREATE_SCRIPT = "create_database.sql";
	private static final String INSERT_SCRIPT = "insert_data.sql";

	private final Context mContext;
	private static Repository mInstance = null;

	private Repository(Context context) {
		super(context, NAME, null, VERSION);
		mContext = context;
	}

	synchronized static public Repository getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new Repository(context);
		}
		return mInstance;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		InputStream in_create_stream = null;
		InputStream in_insert_stream = null;
		try {
			in_create_stream = mContext.getResources().getAssets()
					.open(CREATE_SCRIPT);
			String[] statements = parseSqlFile(in_create_stream);
			for (String statement : statements) {
				db.execSQL(statement);
			}
			in_insert_stream = mContext.getResources().getAssets()
					.open(INSERT_SCRIPT);
			statements = parseSqlFile(in_insert_stream);
			for (String statement : statements) {
				db.execSQL(statement);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in_create_stream != null) {
					in_create_stream.close();
				}
				if (in_insert_stream != null) {
					in_insert_stream.close();
				}
			} catch (IOException e) {
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen(SQLiteDatabase database) {
		super.onOpen(database);
		// enable the foreign key constraint.
		if (!database.isReadOnly()) {
			database.execSQL("PRAGMA foreign_keys = ON;");
		}
	}

	public Toc findTocById(int id) {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		Toc toc = null;
		try {
			db = getReadableDatabase();
			String where = Toc.ID  + " = ?";
			String[] args = new String[]{Integer.toString(id)};
			cursor = db.query(Toc.TABLE, null, where, args, null, null, null);
			if (cursor != null && cursor.getCount() > 0) {
				cursor.moveToNext();
				String title = cursor.getString(cursor
						.getColumnIndex(Toc.TITLE));
				toc = new Toc(id, title);
			}
		} catch (SQLiteException e) {
			e.printStackTrace();
		} finally {
			if (db  != null && db.isOpen()) {
				db.close();
			}
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}
		return toc;
	}

	public List<Toc> getTocs() {
		Cursor cursor = null;
		SQLiteDatabase db = null;
		ArrayList<Toc> tocs = null;
		try {
			db = getReadableDatabase();
			cursor = db.query(Toc.TABLE, null, null, null, null, null, null);
			int count = 0;
			if (cursor != null && (count = cursor.getCount()) > 0) {
				tocs = new ArrayList<Toc>(count);
				while (cursor.moveToNext()) {
					int id = cursor.getInt(cursor.getColumnIndex(Toc.ID));
					String title = cursor.getString(cursor
							.getColumnIndex(Toc.TITLE));
					tocs.add(new Toc(id, title));
				}
			}
		} catch (SQLiteException e) {

		} finally {
			if (db != null && db.isOpen()) {
				db.close();
			}
			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}
		}

		List<Toc> immutableList = null;
		if (tocs != null) {
			immutableList = Collections.unmodifiableList(tocs);
		}
		return immutableList;
	}

	private String[] parseSqlFile(InputStream input) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		String line;
		StringBuilder sql = new StringBuilder();
		String multiLineComment = null;

		while ((line = reader.readLine()) != null) {
			if (multiLineComment == null) {
				if (line.startsWith("/*")) {
					if (!line.endsWith("}"))
						multiLineComment = "/*";
				} else if (line.startsWith("{")) {
					if (!line.endsWith("}"))
						multiLineComment = "{";
				} else if (!line.startsWith("--") && !line.equals("")) {
					sql.append(line);
				}
			} else if (multiLineComment.equals("/*")) {
				if (line.endsWith("*/"))
					multiLineComment = null;
			} else if (multiLineComment.equals("{")) {
				if (line.endsWith("}"))
					multiLineComment = null;
			}
		}
		reader.close();
		return sql.toString().split(";");
	}

}
