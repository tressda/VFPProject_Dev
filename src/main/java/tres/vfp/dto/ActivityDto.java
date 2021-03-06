package tres.vfp.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import tres.domain.Task;
import tres.domain.Users;

public class ActivityDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private int activityId;

	private String description;

	private String status;

	private String weight;
	
	private String taskWeight;
	
	private Date date;

	private Task task;
	private Users user;
	private String createdBy;

	private Date startDate;

	private Date dueDate;

	private Date endDate;

	private Date assignedDate;

	private String genericstatus;
	private int countApproved;
	private int ActivityEscalated;
	private Date createdDate;
	private String type;
	private String weeklyPlan;
	private String taskName;
	private int ActivityFailed;
	private boolean editable;
	private boolean action;
	private boolean commmentAction;
	private boolean replanAction;
	private boolean reportAction;
	private boolean doneAction;
	private boolean planAction;
	private boolean changeAction;
	private boolean editAction;
	private boolean showAction;
	private boolean showPlanedIcon;
	private boolean showNotStartedIcon;
	private boolean approvedComment;
	private boolean failedAction;
	private boolean planFailedAction;
	private boolean editFailedAction;
	private boolean showFailedStartedIcon;
	private boolean failedActIcon;
	private boolean failedEvButton;
	private boolean redIcon;
	private boolean yellowIcon;
	private boolean greenIcon;
	private boolean actredIcon;
	private boolean actyellowIcon;
	private boolean actgreenIcon;
	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public String getGenericstatus() {
		return genericstatus;
	}

	public void setGenericstatus(String genericstatus) {
		this.genericstatus = genericstatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isAction() {
		return action;
	}

	public void setAction(boolean action) {
		this.action = action;
	}

	public boolean isCommmentAction() {
		return commmentAction;
	}

	public void setCommmentAction(boolean commmentAction) {
		this.commmentAction = commmentAction;
	}

	public boolean isReplanAction() {
		return replanAction;
	}

	public void setReplanAction(boolean replanAction) {
		this.replanAction = replanAction;
	}

	public boolean isReportAction() {
		return reportAction;
	}

	public void setReportAction(boolean reportAction) {
		this.reportAction = reportAction;
	}

	public boolean isDoneAction() {
		return doneAction;
	}

	public void setDoneAction(boolean doneAction) {
		this.doneAction = doneAction;
	}

	public boolean isPlanAction() {
		return planAction;
	}

	public void setPlanAction(boolean planAction) {
		this.planAction = planAction;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isChangeAction() {
		return changeAction;
	}

	public void setChangeAction(boolean changeAction) {
		this.changeAction = changeAction;
	}

	public boolean isEditAction() {
		return editAction;
	}

	public void setEditAction(boolean editAction) {
		this.editAction = editAction;
	}

	public boolean isShowAction() {
		return showAction;
	}

	public void setShowAction(boolean showAction) {
		this.showAction = showAction;
	}

	public boolean isShowPlanedIcon() {
		return showPlanedIcon;
	}

	public void setShowPlanedIcon(boolean showPlanedIcon) {
		this.showPlanedIcon = showPlanedIcon;
	}

	public boolean isShowNotStartedIcon() {
		return showNotStartedIcon;
	}

	public void setShowNotStartedIcon(boolean showNotStartedIcon) {
		this.showNotStartedIcon = showNotStartedIcon;
	}

	public boolean isApprovedComment() {
		return approvedComment;
	}

	public void setApprovedComment(boolean approvedComment) {
		this.approvedComment = approvedComment;
	}

	public boolean isFailedAction() {
		return failedAction;
	}

	public void setFailedAction(boolean failedAction) {
		this.failedAction = failedAction;
	}

	public boolean isPlanFailedAction() {
		return planFailedAction;
	}

	public void setPlanFailedAction(boolean planFailedAction) {
		this.planFailedAction = planFailedAction;
	}

	public boolean isEditFailedAction() {
		return editFailedAction;
	}

	public void setEditFailedAction(boolean editFailedAction) {
		this.editFailedAction = editFailedAction;
	}

	public boolean isShowFailedStartedIcon() {
		return showFailedStartedIcon;
	}

	public void setShowFailedStartedIcon(boolean showFailedStartedIcon) {
		this.showFailedStartedIcon = showFailedStartedIcon;
	}

	public int getActivityFailed() {
		return ActivityFailed;
	}

	public void setActivityFailed(int activityFailed) {
		ActivityFailed = activityFailed;
	}

	public boolean isFailedActIcon() {
		return failedActIcon;
	}

	public void setFailedActIcon(boolean failedActIcon) {
		this.failedActIcon = failedActIcon;
	}

	public String getWeeklyPlan() {
		return weeklyPlan;
	}

	public void setWeeklyPlan(String weeklyPlan) {
		this.weeklyPlan = weeklyPlan;
	}

	public boolean isFailedEvButton() {
		return failedEvButton;
	}

	public void setFailedEvButton(boolean failedEvButton) {
		this.failedEvButton = failedEvButton;
	}

	public int getCountApproved() {
		return countApproved;
	}

	public void setCountApproved(int countApproved) {
		this.countApproved = countApproved;

	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public int getActivityEscalated() {
		return ActivityEscalated;
	}

	public void setActivityEscalated(int activityEscalated) {
		ActivityEscalated = activityEscalated;
	}

	public boolean isRedIcon() {
		return redIcon;
	}

	public void setRedIcon(boolean redIcon) {
		this.redIcon = redIcon;
	}

	public boolean isYellowIcon() {
		return yellowIcon;
	}

	public void setYellowIcon(boolean yellowIcon) {
		this.yellowIcon = yellowIcon;
	}

	public boolean isGreenIcon() {
		return greenIcon;
	}

	public void setGreenIcon(boolean greenIcon) {
		this.greenIcon = greenIcon;
	}

	public String getTaskWeight() {
		return taskWeight;
	}

	public void setTaskWeight(String taskWeight) {
		this.taskWeight = taskWeight;
	}

	public boolean isActredIcon() {
		return actredIcon;
	}

	public void setActredIcon(boolean actredIcon) {
		this.actredIcon = actredIcon;
	}

	public boolean isActyellowIcon() {
		return actyellowIcon;
	}

	public void setActyellowIcon(boolean actyellowIcon) {
		this.actyellowIcon = actyellowIcon;
	}

	public boolean isActgreenIcon() {
		return actgreenIcon;
	}

	public void setActgreenIcon(boolean actgreenIcon) {
		this.actgreenIcon = actgreenIcon;
	}

}
