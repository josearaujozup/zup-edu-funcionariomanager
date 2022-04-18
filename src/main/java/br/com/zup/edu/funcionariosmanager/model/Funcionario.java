package br.com.zup.edu.funcionariosmanager.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private LocalDate dataAdmissao;

    @Column(nullable = false)
    private BigDecimal salario;

    public Funcionario(String nome, String cpf, String endereco, LocalDate dataAdmissao, BigDecimal salario) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.dataAdmissao = dataAdmissao;
        this.salario = salario;
    }

    /**
     * @deprecated construtor para uso exclusivo do Hibernate
     */
    @Deprecated
    public Funcionario() {
    }

    public Long getId() {
        return id;
    }

	public void aumento() {
		
		if (!temUmAno()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Funcionario não tem um ano de casa");
		}
		
		this.salario = this.salario.multiply(new BigDecimal("1.10"));
	}
	
	private boolean temUmAno() {
		LocalDate hoje = LocalDate.now();
//		LocalDate dataDoEvento = this.dataAdmissao;
		int prazoEmAnos = Period.between(this.dataAdmissao, hoje).getYears();
		return prazoEmAnos >= 1;
	}
	
}
