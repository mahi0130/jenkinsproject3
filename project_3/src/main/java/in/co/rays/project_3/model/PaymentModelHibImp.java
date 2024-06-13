package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class PaymentModelHibImp implements PaymentModelInt {

	@Override
	public long add(PaymentDTO dto) throws ApplicationException, DuplicateRecordException {

		PaymentDTO existDto = null;

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

	@Override
	public void delete(PaymentDTO dto) throws ApplicationException {

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

	@Override
	public void update(PaymentDTO dto) throws ApplicationException, DuplicateRecordException {

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

	@Override
	public PaymentDTO findByPK(long pk) throws ApplicationException {

		Session session = null;
		PaymentDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (PaymentDTO) session.get(PaymentDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting Bank by pk");
		} finally {
			session.close();
		}

		return dto;

	}

	@Override
	public PaymentDTO findByLogin(String login) throws ApplicationException {

		Session session = null;
		PaymentDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (PaymentDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting Bank by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;

	}

	@Override
	public List list() throws ApplicationException {

		return list(0, 0);
	}

	@Override
	public List list(int pageNo, int pageSize) throws ApplicationException {

		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
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

	@Override
	public List search(PaymentDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List search(PaymentDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub

		Session session = null;
		ArrayList<PaymentDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
			if (dto != null) {
				if (dto.getId() != null && dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));
				}
				if (dto.getName() != null && dto.getName().length() > 0) {
					criteria.add(Restrictions.like("name", dto.getName() + "%"));
				}

				if (dto.getAccountNumber() != null && dto.getAccountNumber().length() > 0) {
					criteria.add(Restrictions.like("accountNumber", dto.getAccountNumber() + "%"));
				}
				if (dto.getAmount() > 0) {
					criteria.add(Restrictions.eq("amount", dto.getAmount()));
				}
				if (dto.getBankName() != null && dto.getBankName().length() > 0) {
					criteria.add(Restrictions.like("bankName", dto.getBankName() + "%"));
				}

			}

			System.out.println("searchcalll");
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<PaymentDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in Bank search");
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public List getRoles(PaymentDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
