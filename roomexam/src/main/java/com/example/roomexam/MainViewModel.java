package com.example.roomexam;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    // 데이터베이스 객채 생성
//    final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,"todo-db").build();
   private AppDatabase db;


    public MainViewModel(@NonNull Application application) {
        super(application);

        db = Room.databaseBuilder(application,AppDatabase.class,"todo-db").build();
    }

    public LiveData<List<Todo>> getAll(){
        return db.todoDao().getAll();

    }
    public void insert(Todo todo){
        new InsertAsyncTask(db.todoDao()).execute(todo);
    }

    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {

        private TodoDao mTodoDao;

        public InsertAsyncTask(TodoDao todoDao) {
            this.mTodoDao = todoDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mTodoDao.insert(todos[0]);
            return null;
        }
    }
}
