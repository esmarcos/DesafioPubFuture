package main.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Despesa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Double valor;
	private LocalDate dataPagamento;
	private LocalDate dataPagamentoEsperado;
	@Enumerated(EnumType.STRING)
	private TipoDespesa tipoDespesa;
	
	@ManyToOne
	private Conta conta;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public LocalDate getDataPagamentoEsperado() {
		return dataPagamentoEsperado;
	}
	public void setDataPagamentoEsperado(LocalDate dataPagamentoEsperado) {
		this.dataPagamentoEsperado = dataPagamentoEsperado;
	}
	public TipoDespesa getTipoDespesa() {
		return tipoDespesa;
	}
	public void setTipoDespesa(TipoDespesa tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}
	public Conta getConta() {
		return conta;
	}
	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	

}
