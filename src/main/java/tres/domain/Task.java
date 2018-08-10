package tres.domain;

/**
 * author Junior
 **/

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Task")
@NamedQuery(name = "Task.findAll", query = "select r from Task r order by v desc")
public class Task implements  Serializable{
    
 private static final long serialVersionUID = 1L;
@Id
@GeneratedValue
@Column(name="taskId")
private int taskId;

@Column(name="description")
private String description;

@Column(name="startDate")
private Date startDate;

@Column(name="endDate")
private Date endDate;

@ManyToOne
@JoinColumn(name="subTask")
private Task subTask;

@ManyToOne
@JoinColumn(name = "strategicPlan")
private StrategicPlan strategicPlan;

public int getTaskId() {
	return taskId;
}

public void setTaskId(int taskId) {
	this.taskId = taskId;
}

public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date startDate) {
	this.startDate = startDate;
}

public Date getEndDate() {
	return endDate;
}

public void setEndDate(Date endDate) {
	this.endDate = endDate;
}

public StrategicPlan getStrategicPlan() {
	return strategicPlan;
}

public Task getSubTask() {
	return subTask;
}

public void setSubTask(Task subTask) {
	this.subTask = subTask;
}

public void setStrategicPlan(StrategicPlan strategicPlan) {
	this.strategicPlan = strategicPlan;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}

}
