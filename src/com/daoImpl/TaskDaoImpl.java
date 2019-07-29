package com.daoImpl;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.dao.TaskDao;
import com.domain.Student;
import com.domain.Task;
import com.utils.JDBCUtils;

public class TaskDaoImpl implements TaskDao {

	@Override
	public int addTask(Task task) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="insert into task(taskName,className,date,teachId,startDate,stopDate) values(?,?,?,?,?,?)";
		qr.update(sql,task.getTaskName(),task.getClassName(),task.getDate(),task.getTeachId(),task.getStartDate(),task.getStopDate());
		String sql2="select id from task where date =?";
		//查询出刚插入数据的id
		Integer id = qr.query(sql2, new ScalarHandler<>(),task.getDate());
		return id;
	}

	@Override
	public List<Student> findAllStu(int className) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT s.* FROM students s WHERE s.subjectId =(SELECT subjectId FROM course WHERE id=?)";
		return qr.query(sql, new BeanListHandler<Student>(Student.class),className);
	}

	@Override
	public void insertTask_stu(Integer stuId, int taskId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql ="insert into stu_task(stuid,taskid) values(?,?)";
		qr.update(sql,stuId,taskId);
	}

	@Override
	public Integer findAllTask_total(int teachId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="select count(*) from task where teachId=?";
		Long l = qr.query(sql, new ScalarHandler<>(),teachId);
		return l.intValue();
	}

	@Override
	public List<Task> findAllTask_rows(int i, int rows, int teachId) throws SQLException {
		// TODO Auto-generated method stub
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		String sql="SELECT t.*,c.className AS courseName FROM task t,course c WHERE teachId = ? AND t.className = c.id ORDER BY className LIMIT ?,?";
		return qr.query(sql, new BeanListHandler<Task>(Task.class),teachId,i,rows);
	}

}
