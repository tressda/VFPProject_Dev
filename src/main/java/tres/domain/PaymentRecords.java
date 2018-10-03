package tres.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "PaymentRecords")
@NamedQuery(name = "PaymentRecords.findAll", query = "select r from PaymentRecords r order by paymentId desc")
public class PaymentRecords extends CommonDomain implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "paymentId")
	private int paymentId;

	@Column(name = "paymentCode", unique = true)
	private String paymentCode;
	
    @Column(name = "paymentDate")
	private Timestamp paymentDate;
	  
	@Column(name = "paymentExpiretionDate")
	private Timestamp paymentExpiretionDate;
	
	@Column(name = "amount")
    private double  amount;
	   
	   
	@Column(name = "currency")
	private String   currency;
	    
	@Column(name = "paymentChanel")
	private String   paymentChanel;
	  
	  
    @Column(name = "paymentStatus")
	private String  paymentStatus;
	
    @Column(name = "bankRefernceNo")
   	private String  bankRefernceNo;
    
    @Column(name = "comment")
   	private String  comment;
 
    @ManyToOne
    @JoinColumn(name = "paymentApprovedBy")
	private Users paymentApprovedBy;
	
    @ManyToOne
    @JoinColumn(name = "institution")
	private Institution institution;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Timestamp getPaymentExpiretionDate() {
		return paymentExpiretionDate;
	}

	public void setPaymentExpiretionDate(Timestamp paymentExpiretionDate) {
		this.paymentExpiretionDate = paymentExpiretionDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPaymentChanel() {
		return paymentChanel;
	}

	public void setPaymentChanel(String paymentChanel) {
		this.paymentChanel = paymentChanel;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getBankRefernceNo() {
		return bankRefernceNo;
	}

	public void setBankRefernceNo(String bankRefernceNo) {
		this.bankRefernceNo = bankRefernceNo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Users getPaymentApprovedBy() {
		return paymentApprovedBy;
	}

	public void setPaymentApprovedBy(Users paymentApprovedBy) {
		this.paymentApprovedBy = paymentApprovedBy;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}


	
	
	
}