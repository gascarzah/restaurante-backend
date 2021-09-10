package com.gafahtec.repository;

import com.gafahtec.model.ResetToken;

public interface IResetTokenRepository extends IGenericRepository<ResetToken, Integer>{
	
	//from ResetToken rt where rt.token = :?
	ResetToken findByToken(String token);

}
