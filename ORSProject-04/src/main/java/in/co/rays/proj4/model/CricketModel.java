package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.proj4.bean.CricketBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CricketModel {

	public Integer nextPk() throws DatabaseException, SQLException {

		Connection conn = null;
		int pk = 0;

		conn = JDBCDataSource.getConnection();
		try {
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_cricket");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DatabaseException("Exception : Exception in getting Pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}

	public long add(CricketBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		CricketBean existBean = findByTeam(bean.getTeamName());

		if (existBean != null) {
			throw new DuplicateRecordException("Team Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into st_cricket values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getPlayerName());
			pstmt.setString(3, bean.getTeamName());
			pstmt.setInt(4, bean.getRuns());
			pstmt.setInt(5, bean.getMatches());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println(i + " Query OK, The rows affected (0.02 sec)" + "\n"
					+ "Records: Added successfully Duplicates: 0  Warnings: 0");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Cricket");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;

	}

	public void update(CricketBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;

		CricketBean existBean = findByTeam(bean.getTeamName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("Team Name Already Exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement(
					"update st_cricket set player = ?, team = ?, runs = ?, matches= ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getPlayerName());
			pstmt.setString(2, bean.getTeamName());
			pstmt.setInt(3, bean.getRuns());
			pstmt.setInt(4, bean.getMatches());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println(i + " Query OK, The rows affected (0.02 sec)" + "\n"
					+ "Records: Updated successfully Duplicates: 0  Warnings: 0");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in update Cricket");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public void delete(CricketBean bean) throws ApplicationException {

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from st_cricket where id = ?");

			pstmt.setLong(1, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();

			System.out.println(i + " Query OK, The rows affected (0.02 sec)" + "\n"
					+ "Records: Deleted successfully Duplicates: 0  Warnings: 0");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Deleted rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in Deleted Cricket");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	public CricketBean findByPk(long pk) throws ApplicationException {

		Connection conn = null;
		CricketBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_cricket where id = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CricketBean();
				bean.setId(rs.getLong(1));
				bean.setPlayerName(rs.getString(2));
				bean.setTeamName(rs.getString(3));
				bean.setRuns(rs.getInt(4));
				bean.setMatches(rs.getInt(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Cricket by PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public CricketBean findByTeam(String team) throws ApplicationException {

		Connection conn = null;
		CricketBean bean = null;

		StringBuffer sb = new StringBuffer("select * from st_cricket where team = ?");

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, team);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CricketBean();
				bean.setId(rs.getLong(1));
				bean.setPlayerName(rs.getString(2));
				bean.setTeamName(rs.getString(3));
				bean.setRuns(rs.getInt(4));
				bean.setMatches(rs.getInt(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Cricket by code");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}

	public List<CricketBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}

	public List<CricketBean> search(CricketBean bean, int pageNo, int pageSize) throws ApplicationException {

		Connection conn = null;
		List<CricketBean> list = new ArrayList<CricketBean>();

		StringBuffer sql = new StringBuffer("select * from st_cricket where 1 = 1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getPlayerName() != null && bean.getPlayerName().length() > 0) {
				sql.append(" and player like '" + bean.getPlayerName() + "%'");
			}
			if (bean.getTeamName() != null && bean.getTeamName().length() > 0) {
				sql.append(" and team like '" + bean.getTeamName() + "%'");
			}

			if (bean.getRuns() > 0) {
				sql.append(" and runs = '" + bean.getRuns() + "'");
			}

			if (bean.getMatches() > 0) {
				sql.append(" and matches = '" + bean.getMatches() + "'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CricketBean();
				bean.setId(rs.getLong(1));
				bean.setPlayerName(rs.getString(2));
				bean.setTeamName(rs.getString(3));
				bean.setRuns(rs.getInt(4));
				bean.setMatches(rs.getInt(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Cricket by Search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

}
