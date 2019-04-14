package br.usjt.apivolei.maestro.model.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.apivolei.maestro.model.bean.SocioTorcedor;
import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.bean.Usuario;
import br.usjt.apivolei.maestro.model.repository.TorcedorRepository;
import br.usjt.apivolei.maestro.model.service.auth.Auth;

@Service
public class TorcedorService {

	@Autowired
	private TorcedorRepository repo;

	public void cadastrar(Torcedor torcedor) {
		repo.save(torcedor);
	}

	public Torcedor getTorcedor(Long id) {
		return repo.findById(id).get();
	}

	public Torcedor logar(Usuario usuario) {
		if (usuario.getEmail() != null && usuario.getSenha() != null) {
			Torcedor torcedor = repo.findOneByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
			if (torcedor != null) {
				String token = UUID.randomUUID().toString();
				Auth.putToken(token, torcedor.getId());
				torcedor.setAdditionalProperty("token", token);
				return torcedor;
			}
		}
		return null;
	}

	public boolean logout(Usuario usuario) {
		String token = usuario.getToken();
		if (token != null && Auth.isValidToken(token)) {
			Auth.removeToken(token);
			return true;
		}
		return false;
	}

	public boolean tornarSocio(SocioTorcedor socio) {
		String token = socio.getToken();
		if (token != null && Auth.isValidToken(token) && socio.getCelular() != null && socio.getCpf() != null
				&& socio.getDataNascimento() != null && socio.getEndereco() != null
				&& ("M".equals(socio.getGenero()) || "F".equals(socio.getGenero()) || "O".equals(socio.getGenero()))) {
			Long id = Auth.getId(token);
			Torcedor torcedor = repo.findById(id).get();
			
//			if (!torcedor.isSocio()) {
				torcedor.setSocio(true);
				torcedor.setCpf(socio.getCpf());
				torcedor.setDataNascimento(socio.getDataNascimento());
				torcedor.setEndereco(socio.getEndereco());
				torcedor.setCelular(socio.getCelular());
				torcedor.setGenero(socio.getGenero());
				repo.save(torcedor);
				return true;
//			}
		}
		return false;
	}
}