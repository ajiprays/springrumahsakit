package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.TransaksiInap;

@Repository
public class TransaksiInapDao extends ParentDao{
	
	@Transactional
	public void save(TransaksiInap trxInap) {
		super.entityManager.merge(trxInap);
	}
	
	@Transactional
	public void delete(UUID id) {
		TransaksiInap trxInap = findById(id);
		super.entityManager.remove(trxInap);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public TransaksiInap findById(UUID id) {	
		
		List<TransaksiInap> list = super.entityManager
                .createQuery("from TransaksiInap where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new TransaksiInap();
		}
		else {
			return (TransaksiInap)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<TransaksiInap> findAll() {	
		
		List<TransaksiInap> list = super.entityManager
                .createQuery("from TransaksiInap ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TransaksiInap> findByFilter(String pasien,String dokter) {	
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.id_pasien , p.id_dokter , p.total_biaya ");
		sb.append("FROM inap p ");
		sb.append(" JOIN pasien ps ON p.id_pasien=ps.id ");
		sb.append(" JOIN dokter d ON p.id_dokter=d.id " );
		sb.append(" WHERE 1=1 ");		
			
		if(!pasien.equals("null") ) {
			sb.append(" AND ps.nama LIKE '%"+pasien+"%' ");
			System.out.println("not null pasien");
		}
		if(!dokter.equals("null")) {
			sb.append(" AND d.nama LIKE '%"+dokter+"%' ");
			System.out.println("not null dokter");
		}
			
		List<TransaksiInap> list=super.entityManager.createNativeQuery(sb.toString(),TransaksiInap.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<TransaksiInap>();
		}else {
			return list;
		}
	}
	
	public boolean isExist(UUID id) {
		
		if(findById(id).getId()==null) {
			return false;
		}else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public TransaksiInap findByBk(String kode) {	
		
		List<TransaksiInap> list = super.entityManager
                .createQuery("from TransaksiInap where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new TransaksiInap();
		}
		else {
			return (TransaksiInap)list.get(0);
		}
	}

	public boolean isBkExist(String kode) {
		
		if(findByBk(kode).getKode()==null) {
			System.out.println("null")
			;return false;
		}else {
			return true;
		}	 
	}
}
