package com.aji.rumahsakit.rs.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aji.rumahsakit.rs.model.transaksi.TransaksiObat;

@Repository
public class TransaksiObatDao extends ParentDao{

	@Transactional
	public void save(TransaksiObat trxObat) {
		super.entityManager.merge(trxObat);
	}
	
	@Transactional
	public void delete(UUID id) {
		TransaksiObat trxObat = findById(id);
		super.entityManager.remove(trxObat);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public TransaksiObat findById(UUID id) {	
		
		List<TransaksiObat> list = super.entityManager
                .createQuery("from TransaksiObat where id=:id")
                .setParameter("id", id)
                .getResultList();

		if (list.size() == 0) {
			return new TransaksiObat();
		}
		else {
			return (TransaksiObat)list.get(0);
		}
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<TransaksiObat> findAll() {	
		
		List<TransaksiObat> list = super.entityManager
                .createQuery("from TransaksiObat ")
                .getResultList();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<TransaksiObat> findByFilter(String tanggal) {	
		
		StringBuilder sb=new StringBuilder("SELECT p.id, p.kode , p.tanggal , p.total_harga ");
		sb.append("FROM transaksi_obat p ");
		sb.append(" WHERE 1=1 ");		
			
		if(!tanggal.equals("null") ) {
			sb.append(" AND p.tanggal = '"+tanggal+"' ");
			System.out.println("not null nama");
		}
			
		List<TransaksiObat> list=super.entityManager.createNativeQuery(sb.toString(),TransaksiObat.class).getResultList();
		
		if(list.size()==0) {
			return new ArrayList<TransaksiObat>();
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
	public TransaksiObat findByBk(String kode) {	
		
		List<TransaksiObat> list = super.entityManager
                .createQuery("from TransaksiObat where kode=:kode")
                .setParameter("kode", kode)
                .getResultList();

		if (list.size() == 0) {
			return new TransaksiObat();
		}
		else {
			return (TransaksiObat)list.get(0);
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
