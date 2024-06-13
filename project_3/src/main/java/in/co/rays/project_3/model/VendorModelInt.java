package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.VendorDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.exception.RecordNotFoundException;


public interface VendorModelInt  {
	public long add(VendorDTO dto)throws ApplicationException,DuplicateRecordException;
	public void delete(VendorDTO dto)throws ApplicationException;
	public void update(VendorDTO dto)throws ApplicationException,DuplicateRecordException;
	public VendorDTO findByPK(long pk)throws ApplicationException;
	public VendorDTO findByLogin(String login)throws ApplicationException;
	public List list()throws ApplicationException;
	public List list(int pageNo,int pageSize)throws ApplicationException;
	public List search(VendorDTO dto,int pageNo,int pageSize)throws ApplicationException;
	public List search(VendorDTO dto)throws ApplicationException;
	public List getRoles(VendorDTO dto)throws ApplicationException;
	
	
	

	

}
