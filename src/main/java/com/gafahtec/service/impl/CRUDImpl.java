package com.gafahtec.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.service.ICRUD;
import com.gafahtec.socket.service.WebSocketService;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract IGenericRepository<T, ID> getRepo();

	
//	protected abstract String getEntityTopic();
	
	@Override
	public T registrar(T t) throws Exception {
		return getRepo().save(t);
	}

	@Override
	public T modificar(T t) throws Exception {
		return getRepo().save(t);
	}

	@Override
	public List<T> listar() throws Exception {		
		return getRepo().findAll();
	}

	@Override
	public T listarPorId(ID id) throws Exception {
		return getRepo().findById(id).orElse(null);
	}

	@Override
	public void eliminar(ID id) throws Exception {
		getRepo().deleteById(id);
	}
//	@Autowired
//	private WebSocketService webSocketService;
//    private void notifyFrontend() {
//        final String entityTopic = getEntityTopic();
//        if (entityTopic == null) {
//            LOG.error("Failed to get entity topic");
//            return;
//        }
//
//        webSocketService.sendMessage(entityTopic);
//    }

}
