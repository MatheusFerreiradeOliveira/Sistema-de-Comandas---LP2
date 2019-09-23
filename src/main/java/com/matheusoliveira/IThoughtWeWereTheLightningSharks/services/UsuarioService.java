package com.matheusoliveira.IThoughtWeWereTheLightningSharks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Permissoes;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.domain.Usuario;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.PermissoesRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.repository.UsuarioRepository;
import com.matheusoliveira.IThoughtWeWereTheLightningSharks.services.exception.ObjectNotFoundException;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PermissoesRepository permissoesRepository;
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(String id) {
		Usuario user = usuarioRepository.findOne(id);
		if(user==null) {
			throw new ObjectNotFoundException("Objeto nÃ£o encontrado");  
		}
		return user;
	}
		
	public Usuario insert(Usuario user) {
		//Checar as permissoes aqui
		return usuarioRepository.insert(user);
	}
	
	public Usuario update(Usuario user) {
		Usuario obj = findById(user.getId());
		change(obj, user);
		return usuarioRepository.save(obj);
	}
	
	public void change(Usuario user1, Usuario user2) {
		if(user2.getNome()!=null)
			user1.setNome(user2.getNome());
		
		Permissoes permissao;
		
		/*if(user2.getPermissoes()!=null) {
			for(Permissoes x : user2.getPermissoes()) {
				String idX = x.getId();
				permissao = permissoesRepository.findOne(idX);
				if(permissao == null) {
					throw new ObjectNotFoundException("Objeto nÃ£o encontrado"); 
				}
			}
		}else {
			throw new ObjectNotFoundException("Objeto nÃ£o encontrado: NÃ£o existe produto");
		}*/
		user1.setPermissoes(user2.getPermissoes());
	}
	
	public void deleteById(String id) {
		usuarioRepository.delete(id);
	}
}
