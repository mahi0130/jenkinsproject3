package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.VendorDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.exception.RecordNotFoundException;
import in.co.rays.project_3.util.EmailBuilder;
import in.co.rays.project_3.util.EmailMessage;
import in.co.rays.project_3.util.EmailUtility;
import in.co.rays.project_3.util.HibDataSource;

public class VendorModelHibImp  implements  VendorModelInt{

	
	public long add(VendorDTO dto) throws ApplicationException, DuplicateRecordException {


		VendorDTO existDto = null;
		
		Session session = HibDataSource.getSession();
		Transaction tx = null;
		try {

			tx = session.beginTransaction();

			session.save(dto);

			dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in Bank Add " + e.getMessage());
		} finally {
			session.close();
		}
		return dto.getId();

	}

	
	public void delete(VendorDTO dto) throws ApplicationException {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Bank Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}


	public void update(VendorDTO dto) throws ApplicationException, DuplicateRecordException {
		Session session = null;
		
		Transaction tx = null;

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Bank update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	

	public VendorDTO findByPK(long pk) throws ApplicationException {
		Session session = null;
		VendorDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (VendorDTO) session.get(VendorDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	

	public VendorDTO findByLogin(String login) throws ApplicationException {
		Session session = null;
		VendorDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(VendorDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (VendorDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting Bank by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(VendorDTO.class);
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);

			}
			list = criteria.list();

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in  Banks list");
		} finally {
			session.close();
		}

		return list;
	}

	
	public List search(VendorDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	
	public List search(VendorDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub

		Session session = null;
		ArrayList<VendorDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(VendorDTO.class);
			if (dto != null) {
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getName() != null && dto.getName().length() > 0) {
					criteria.add(Restrictions.like("name", dto.getName() + "%"));
				}
				
				  if (dto.getAddress() != null && dto.getAddress().length() > 0) {
				  criteria.add(Restrictions.like("address", dto.getAddress() + "%"));
				  }
				if (dto.getAccountNumber() != null && dto.getAccountNumber().length() > 0) {
					criteria.add(Restrictions.eq("accountNumber", dto.getAccountNumber()));
				}
				
			}
			
			System.out.println("searchcalll");
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<VendorDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Bank search");
		} finally {
			session.close();
		}

		return list;
	}

	

	public List getRoles(VendorDTO dto) throws ApplicationException {
		return null;
	}



	
	

}
