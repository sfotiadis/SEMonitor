package Models;

import java.util.Date;

public class ReleaseInfo {

	public static final boolean operations = true;
	public static final boolean dataStructures = false;
	
	/**
	 * The sequential id of the release
	 */
	private int id;
	
	/**
	 * The date of the release
	 */
	private Date date;
	
	/**
	 * Change Records for operations and data structures
	 */
	private ChangeRecord[] changeRecords;
	
	/**
	 * Metrics Records for operations and data structures
	 */
	private MetricsRecord[] metricsRecords;
	
	/**
	 * Constructor
	 * @param id 
	 * @param date 
	 * @param opData 
	 * @param dsData 
	 * @param prDate 
	 */
	public ReleaseInfo(int id, Date date, ChangeRecord opChRd, ChangeRecord dsChRd, 
			MetricsRecord opMtRd, MetricsRecord dsMtRd) {
		this.id = id;
		this.date = date;
		
		changeRecords = new ChangeRecord[2];
		metricsRecords = new MetricsRecord[2];
		
		changeRecords[0] = opChRd;
		changeRecords[1] = dsChRd;
		metricsRecords[0] = opMtRd; 
		metricsRecords[1] = dsMtRd; 
	}
	
	/**
	 * Getter of id
	 */
	public int getId() {
	 	 return id; 
	}

	/**
	 * Getter of the date
	 */
	public Date getDate() {
	 	 return date; 
	}
	
	/**
	 * Generic getter of the Changes
	 */
	public ChangeRecord getChangesRecord(boolean opOrDs) { 
		return opOrDs ? changeRecords[0] : changeRecords[1];
	 }
	
	/**
	 * Generic getter of metrics
	 */
	public MetricsRecord getMetricsRecord(boolean opOrDs) { 
		return opOrDs ? metricsRecords[0] : metricsRecords[1];
	 }
	

	@Override
	public String toString() {
		return "Id: " + id + ", Date: " + date + 
				"\nOperations\n" + changeRecords[0] + "\n" + metricsRecords[0] +
				"\nData Structures\n" + changeRecords[1] + "\n" + metricsRecords[1] + "\n";
	}
}
