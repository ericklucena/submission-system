package co.codehaven.submissionsystem.business;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="Status")
public class Status {
	@Id
	private String server;
	
	private String lista;
	private int number;

	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;
	
	private int endDay;
	private int endMonth;
	private int endYear;
	private int endHour;
	private int endMinute;
	
	
	public Status(String server, String lista, int number, int day, int month, int year,
			int hour, int minute, int endDay, int endMonth, int endYear,
			int endHour, int endMinute) {
		super();
		this.server = server;
		this.lista = lista;
		this.number = number;
		this.day = day;
		this.month = month;
		this.year = year;
		this.hour = hour;
		this.minute = minute;
		this.endDay = endDay;
		this.endMonth = endMonth;
		this.endYear = endYear;
		this.endHour = endHour;
		this.endMinute = endMinute;
	}
	
	public String getDate(){
		return ""+
				String.format("%02d", this.day)+"/"+
				String.format("%02d", this.month)+"/"+
				this.year+" "+
				String.format("%02d", this.hour)+":"+
				String.format("%02d", this.minute);
	}
	
	public String getEndDate(){
		return ""+
				String.format("%02d", this.endDay)+"/"+
				String.format("%02d", this.endMonth)+"/"+
				this.endYear+" "+
				String.format("%02d", this.endHour)+":"+
				String.format("%02d", this.endMinute);
	}
	
	public String getServerDate(){
		return ""+
				this.month+"/"+
				this.day+"/"+
				this.year+" "+
				this.hour+":"+
				this.minute;
	}
	
	public String getServerEndDate(){
		return ""+
				String.format("%02d", this.endMonth)+"/"+
				String.format("%02d", this.endDay)+"/"+				
				this.endYear+" "+
				String.format("%02d", this.endHour)+":"+
				String.format("%02d", this.endMinute);
	}
	
	public String getConfiguration(){
		return this.lista+" "+this.getNumber()+" "+this.getDate()+" "+this.getEndDate();
	}


	public String getServer() {
		return server;
	}


	public String getLista() {
		return lista;
	}


	public int getDay() {
		return day;
	}


	public int getMonth() {
		return month;
	}


	public int getYear() {
		return year;
	}


	public int getHour() {
		return hour;
	}


	public int getMinute() {
		return minute;
	}


	public int getEndDay() {
		return endDay;
	}


	public int getEndMonth() {
		return endMonth;
	}


	public int getEndYear() {
		return endYear;
	}


	public int getEndHour() {
		return endHour;
	}


	public int getEndMinute() {
		return endMinute;
	}
	
	public int getNumber(){
		return this.number;
	}
	

		
	
}
