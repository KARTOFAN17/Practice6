package com.mirea.grachev.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = App.getInstance().getDatabase();
        EmployeeDao employeeDao = db.employeeDao();
        Employee employee = new Employee();
        employee.name = "John Smith";
        employee.salary = 10000;
        employeeDao.insert(employee);
        List<Employee> employees = employeeDao.getAll();
        employee = employeeDao.getById(1);
        employee.salary = 20000;
        employeeDao.update(employee);
        Log.d("EMPLOYE", employee.name + " " + employee.salary + " "+ employee.id);
    }

    @Entity
    public static class Employee {
        @PrimaryKey(autoGenerate = true)
        public long id;
        public String name;
        public int salary;
    }

    @Dao
    public interface EmployeeDao {
        @Query("SELECT * FROM employee")
        List<Employee> getAll();
        @Query("SELECT * FROM employee WHERE id = :id")
        Employee getById(long id);
        @Insert
        void insert(Employee employee);
        @Update
        void update(Employee employee);
        @Delete
        void delete(Employee employee);
    }

    @Database(entities = {MainActivity.Employee.class}, version = 1)
    abstract static class AppDatabase extends RoomDatabase {
        public abstract MainActivity.EmployeeDao employeeDao();
    }
}
