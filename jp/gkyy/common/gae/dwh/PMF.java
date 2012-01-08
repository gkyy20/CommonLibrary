package jp.gkyy.common.gae.dwh;

import javax.jdo.*;

public final class PMF {
	private static final PersistenceManagerFactory INSTANCE = 
		JDOHelper.getPersistenceManagerFactory("transactions-optional");
	public static PersistenceManagerFactory get(){
		return INSTANCE;
	}
	public static final PersistenceManager createManager(){
		return get().getPersistenceManager();		                                     
	}
	private PMF(){
	}
}
