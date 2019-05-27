package br.usjt.apivolei.maestro.model.service;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.usjt.apivolei.maestro.model.bean.Convenio;
import br.usjt.apivolei.maestro.model.bean.DetalhesRetorno;
import br.usjt.apivolei.maestro.model.bean.Torcedor;
import br.usjt.apivolei.maestro.model.interfaces.IPontoTorcedor;
import br.usjt.apivolei.maestro.model.interfaces.PontoTorcedorImpl;
import br.usjt.apivolei.maestro.model.repository.ConvenioRepository;
import br.usjt.apivolei.maestro.model.repository.TorcedorRepository;

@Service
public class TorcedorService {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	@Autowired
	private DetalhesRetorno retorno;
	
	@Autowired
	private TorcedorRepository repTorcedor;

	@Autowired
	private ConvenioRepository repConvenio;

	public ResponseEntity<?> cadastrar(Torcedor torcedor, HttpServletRequest request) {

		torcedor.setContaAtiva(true);
		torcedor.setSocio(false);
		Torcedor t = repTorcedor.save(torcedor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(t.getId()).toUri();

		return ResponseEntity.created(uri).body(this.retorno.build(new Date(), "Cadastro realizado", "uri=" + request.getRequestURI()));
	}

	public ResponseEntity<?> logar(Torcedor usuario, HttpServletRequest request) {
		
		if (usuario.getEmail() != null && usuario.getSenha() != null) {
			
			Torcedor torcedor = repTorcedor.findOneByEmailAndSenha(usuario.getEmail(), usuario.getSenha());
			
			if (torcedor != null) {
				
				if (torcedor.isContaAtiva()) {
					
					return ResponseEntity.ok(torcedor);
				} else {
					return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
				}
			}
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.retorno.build(new Date(), "Acesso negado", "uri="+request.getRequestURI()));
	}

	public ResponseEntity<?> getTorcedor(Long id) {
		return ResponseEntity.ok().header("Content-Type", MediaType.APPLICATION_JSON.toString()).body(repTorcedor.findById(id).get());
	}

	public Torcedor buscarTorcedor(Long id) {
		return repTorcedor.findByIdAndContaAtiva(id, true).orElseThrow(NoSuchElementException::new);
	}

	public ResponseEntity<?> souSocio(Long id, HttpServletRequest request) {
		
		Torcedor torcedor = repTorcedor.findById(id).get();
		
		if (torcedor.isContaAtiva()) {
			
			Map<String, Object> retorno = new HashMap<String, Object>();
			retorno.put("id", id);
			retorno.put("socio", repTorcedor.findById(id).get().isSocio());
			return ResponseEntity.ok(retorno);
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
	}

	public ResponseEntity<?> serSocio(Long id, Torcedor socio, HttpServletRequest request) {
		if (camposObrigatoriosOK(socio)) {
			Torcedor torcedor = repTorcedor.findById(id).get();
			
			if (torcedor.isContaAtiva()) {

				if (!torcedor.isSocio()) {

					torcedor.setSocio(true);
					torcedor.setCpf(socio.getCpf());
					torcedor.setDataNascimento(socio.getDataNascimento());
					torcedor.setEndereco(socio.getEndereco());
					torcedor.setCelular(socio.getCelular());
					torcedor.setGenero(socio.getGenero());
					torcedor.setDataUltimaPontuacao(socio.getDataNascimento());
					torcedor.setPontos(0);
					repTorcedor.save(torcedor);
					
					return ResponseEntity.ok(this.retorno.build(new Date(), "Sucesso", "uri=" + request.getRequestURI()));
				} else {
					return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "Usuário já é sócio", "uri=" + request.getRequestURI()));
				}
			} else {
				return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
			}
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "Parametros inválidos", "uri=" + request.getRequestURI()));
	}

	private boolean camposObrigatoriosOK(Torcedor socio) {
		return socio.getCelular() != null
				&& !"".equals(socio.getCelular())
				&& socio.getCpf() != null
				&& !"".equals(socio.getCpf())
				&& socio.getDataNascimento() != null
				&& socio.getEndereco() != null
				&& !"".equals(socio.getEndereco())
				&& ("M".equals(socio.getGenero()) || "F".equals(socio.getGenero()) || "O".equals(socio.getGenero()));
	}

	public ResponseEntity<?> desativarConta(Long id, HttpServletRequest request) {
		
		Torcedor torcedor = repTorcedor.findById(id).get();
		
		if (torcedor.isContaAtiva()) {
			torcedor.setContaAtiva(false);
			repTorcedor.save(torcedor);
			return ResponseEntity.ok(this.retorno.build(new Date(), "Sucesso, conta desativada", "uri=" + request.getRequestURI()));
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "Conta já está desativada", "uri=" + request.getRequestURI()));
	}

	public ResponseEntity<?> ativarConta(Long id, HttpServletRequest request) {
		
		Torcedor torcedor = repTorcedor.findById(id).get();
		
		if (!torcedor.isContaAtiva()) {
			torcedor.setContaAtiva(true);
			repTorcedor.save(torcedor);
			return ResponseEntity.ok(this.retorno.build(new Date(), "Sucesso, conta ativada", "uri=" + request.getRequestURI()));
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "Conta já está ativada", "uri=" + request.getRequestURI()));
	}

	public ResponseEntity<?> alterarDados(Long id, Torcedor torcedorParam, HttpServletRequest request) {
		
		Torcedor torcedor = repTorcedor.findById(id).get();
		
		if (torcedor.isContaAtiva()) {
			
			torcedor.setNome(torcedorParam.getNome());
			torcedor.setEmail(torcedorParam.getEmail());
			
			if (torcedor.isSocio()) {
				torcedor.setCelular(torcedorParam.getCelular() != null ? torcedorParam.getCelular() : torcedor.getCelular());
				torcedor.setCpf(torcedorParam.getCpf() != null ? torcedorParam.getCpf() : torcedor.getCpf());
				torcedor.setDataNascimento(torcedorParam.getDataNascimento() != null ? torcedorParam.getDataNascimento() : torcedor.getDataNascimento());
				torcedor.setEndereco(torcedorParam.getEndereco() != null ? torcedorParam.getEndereco() : torcedor.getEndereco());
				torcedor.setGenero(torcedorParam.getGenero() != null ? torcedorParam.getGenero() : torcedor.getGenero());
				torcedor.setPontos(torcedorParam.getPontos() != null ? torcedorParam.getPontos() : torcedor.getPontos());
			}
			repTorcedor.save(torcedor);
			return ResponseEntity.ok(this.retorno.build(new Date(), "Dados alterados", "uri=" + request.getRequestURI()));
		}

		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
	}

	public ResponseEntity<?> excluir(Long id, HttpServletRequest request) {
		repTorcedor.delete(repTorcedor.findById(id).get());
		return ResponseEntity.ok(this.retorno.build(new Date(), "Objeto excluído", "uri=" + request.getRequestURI()));
	}

	public ResponseEntity<?> naoSerSocio(Long id, HttpServletRequest request) {

		Torcedor torcedor = repTorcedor.findById(id).get();

		if (torcedor.isContaAtiva()) {

			torcedor.setSocio(false);
			repTorcedor.save(torcedor);
			return ResponseEntity.ok(this.retorno.build(new Date(), "Sucesso", "uri=" + request.getRequestURI()));
		}
		
		return ResponseEntity.badRequest().body(this.retorno.build(new Date(), "A conta está desativada", "uri="+request.getRequestURI()));
	}
	
	public ResponseEntity<?> pontuarComQRCode(Long idConvenio, Long idTorcedor, HttpServletRequest request) {

		Torcedor torcedor = repTorcedor.findById(idTorcedor).get();
		Convenio convenio = repConvenio.findById(idConvenio).get();

		DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
		Calendar c = Calendar.getInstance();
		String dataUltimaPontucao = f.format(torcedor.getDataUltimaPontuacao().getTime());
		String dataAtual = f.format(c.getTime());
		
		if (torcedor.isContaAtiva() 
				&& torcedor.isSocio()
					&& !dataUltimaPontucao.equals(dataAtual)) {

				IPontoTorcedor pontos = new PontoTorcedorImpl(torcedor.getPontos());
				torcedor.setPontos(pontos.incrementar(convenio.getPontuacaoQRCode()));
				torcedor.setDataUltimaPontuacao(c);
				
				repTorcedor.save(torcedor);
				return ResponseEntity.ok(this.retorno.build(new Date(), "Pontuação incrementada na conta do torcedor",
						"uri=" + request.getRequestURI()));
		}

		return ResponseEntity.badRequest().body(this.retorno.build(new Date(),
				"Verifique se o torcedor está ativo ou se o mesmo é um sócio", "uri=" + request.getRequestURI()));
	}
}