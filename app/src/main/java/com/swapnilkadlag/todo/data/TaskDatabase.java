package com.swapnilkadlag.todo.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class}, version = 1,exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {
    private static TaskDatabase instance;

    public abstract TaskDao taskDao();

    public static synchronized TaskDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private TaskDao taskDao;

        private PopulateDbAsyncTask(TaskDatabase db) {
            taskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.insert(new Task("Mean them very", "As absolute is by amounted repeated entirely ye returned. These ready timed enjoy might sir yet one since. Years drift never if could forty being no.", true));
            taskDao.insert(new Task("Absolute entrance", "On estimable dependent as suffering on my. Rank it long have sure in room what as he.", false));
            taskDao.insert(new Task("One preferred sportsmen", "At as in understood an remarkably solicitude.", true));
            taskDao.insert(new Task("Mean them very", "As absolute is by amounted repeated entirely ye returned. These ready timed enjoy might sir yet one since. Years drift never if could forty being no.", true));
            taskDao.insert(new Task("Absolute entrance", "On estimable dependent as suffering on my. Rank it long have sure in room what as he.", false));
            taskDao.insert(new Task("One preferred sportsmen", "At as in understood an remarkably solicitude.", true));
            taskDao.insert(new Task("Mean them very", "As absolute is by amounted repeated entirely ye returned. These ready timed enjoy might sir yet one since. Years drift never if could forty being no.", true));
            taskDao.insert(new Task("Absolute entrance", "On estimable dependent as suffering on my. Rank it long have sure in room what as he.", false));
            taskDao.insert(new Task("One preferred sportsmen", "At as in understood an remarkably solicitude.", true));
            taskDao.insert(new Task("Mean them very", "As absolute is by amounted repeated entirely ye returned. These ready timed enjoy might sir yet one since. Years drift never if could forty being no.", true));
            taskDao.insert(new Task("Absolute entrance", "On estimable dependent as suffering on my. Rank it long have sure in room what as he.", false));
            taskDao.insert(new Task("One preferred sportsmen", "At as in understood an remarkably solicitude.", true));
            taskDao.insert(new Task("Mean them very", "As absolute is by amounted repeated entirely ye returned. These ready timed enjoy might sir yet one since. Years drift never if could forty being no.", true));
            taskDao.insert(new Task("Absolute entrance", "On estimable dependent as suffering on my. Rank it long have sure in room what as he.", false));
            taskDao.insert(new Task("One preferred sportsmen", "At as in understood an remarkably solicitude.", true));
            taskDao.insert(new Task("Mean them very", "As absolute is by amounted repeated entirely ye returned. These ready timed enjoy might sir yet one since. Years drift never if could forty being no.", true));
            taskDao.insert(new Task("Absolute entrance", "On estimable dependent as suffering on my. Rank it long have sure in room what as he.", false));
            taskDao.insert(new Task("One preferred sportsmen", "At as in understood an remarkably solicitude.", true));
            taskDao.insert(new Task("Mean them very", "As absolute is by amounted repeated entirely ye returned. These ready timed enjoy might sir yet one since. Years drift never if could forty being no.", true));
            taskDao.insert(new Task("Absolute entrance", "On estimable dependent as suffering on my. Rank it long have sure in room what as he.", false));
            taskDao.insert(new Task("One preferred sportsmen", "At as in understood an remarkably solicitude.", true));
            return null;
        }
    }
}
