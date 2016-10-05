package br.com.devschool.atendimento.servico;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.devschool.animal.servico.AnimalServico;
import br.com.devschool.cliente.servico.ClienteServico;
import br.com.devschool.entidade.Animal;
import br.com.devschool.entidade.Atendimento;
import br.com.devschool.entidade.Cliente;
import br.com.devschool.util.Servico;

public class AtendimentoServico implements Servico<Atendimento> {
	
	private AtendimentoDAO dao;
	private ClienteServico clienteServico;
	private AnimalServico animalServico;
	
	public AtendimentoServico(EntityManager em) {
		dao = new AtendimentoDAO(em);
		clienteServico = new ClienteServico(em);
		animalServico = new AnimalServico(em);
	}

	public Atendimento consultarPor(Integer id) {
		return dao.consultarPor(id);
	}
	
	public Atendimento salvar(Atendimento atendimento) {
		if (atendimento.getId() != null && atendimento.getId() > 0) {
			return atualizar(atendimento);
		}
		atendimento = dao.salvar(atendimento);
		return atendimento;
	}
	
	public Atendimento atualizar(Atendimento atendimento) {
		atendimento = dao.atualizar(atendimento); 
		return atendimento;
	}
	
	public void excluir(Integer id) {
		dao.excluir(id);
	}
	
	public List<Atendimento> consultar() {
		return dao.consultar();
	}
	
	public List<Cliente> consultarClientes(String nome) {
		nome = nome.concat("%");
		return clienteServico.consultarPor(nome);
	}

	public List<Atendimento> consultarPor(Cliente cliente) {
		if (cliente == null || cliente.getId() == null) {
			throw new IllegalArgumentException("É obrigatório selecionar um Cliente.");
		}
		return dao.consultarPor(cliente);
	}

	public List<Animal> consultarAnimaisPor(Cliente cliente) {
		return animalServico.consultarPor(cliente);
	}
}
