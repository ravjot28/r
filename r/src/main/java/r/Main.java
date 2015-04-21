package r;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.rank.Median;

public class Main {

	public static final String DRIVER = "org.postgresql.Driver";
	public static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/SoftwareReEngg";
	private static Connection connection = null;
	public static PreparedStatement preparedStatement = null;

	public static void main(String[] args) throws SQLException {
		List<Double> list = new ArrayList<Double>();
		try {
			ResultSet rs = null;

			Class.forName(DRIVER).newInstance();
			connection = DriverManager.getConnection(DATABASE_URL, "postgres",
					"admin");
			preparedStatement = connection
					.prepareStatement("select a from b where x = 1");
			rs = preparedStatement.executeQuery();
			list = new ArrayList<Double>();
			while (rs.next()) {
				list.add(rs.getDouble(1));
			}
			rs.close();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}

		double mean = new Main().getMean(getArray(list));
		System.out.println(mean);
	}

	public static double[] getArray(List<Double> list) {
		double[] d = new double[list.size()];
		int i = 0;
		for (double d1 : list) {
			d[i] = d1;
		}
		return d;
	}

	public double getMedian(double[] data) {
		Median median = new Median();
		return median.evaluate(data);
	}

	public double getMean(double[] data) {
		Mean median = new Mean();
		return median.evaluate(data);
	}
}
