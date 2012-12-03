package estoque;

import java.util.Calendar;

public class Produto {
   
	private String nome;
	
	private String codigo;
	
    private double valordeCompra;
   
    private double valordeVenda;
    
    private double quantidadeemEstoque;
  
    private double quantidedeVendido;
    
    private double limiteMinimoemEstoque;
    
    private String categoria;

    private String fornecedor;

    private int promocao;
   
    private Calendar menorValidadeemEstoque;

    public Produto() {
    }
    
    public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setPromocao(int promocao) {
		this.promocao = promocao;
	}

	public double getValordeCompra() {
        return valordeCompra;
    }

    public void setValordeCompra(double valordeCompra) {
        this.valordeCompra = valordeCompra;
    }

    public double getValordeVenda() {
        return valordeVenda;
    }

    public void setValordeVenda(double valordeVenda) {
        this.valordeVenda = valordeVenda;
    }

    public double getQuantidadeemEstoque() {
        return quantidadeemEstoque;
    }

    public void setQuantidadeemEstoque(double quantidadeemEstoque) {
        this.quantidadeemEstoque = quantidadeemEstoque;
    }

    public double getQuantidedeVendido() {
        return quantidedeVendido;
    }

    public void setQuantidedeVendido(double quantidedeVendido) {
        this.quantidedeVendido = quantidedeVendido;
    }

    public double getLimiteMinimoemEstoque() {
        return limiteMinimoemEstoque;
    }

    public void setLimiteMinimoemEstoque(double limiteMinimoemEstoque) {
        this.limiteMinimoemEstoque = limiteMinimoemEstoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public int getPromocao() {
        return promocao;
    }

    public void setPromocao(Integer promocao) {
        this.promocao = promocao;
    }

    public Calendar getMenorValidadeemEstoque() {
        return menorValidadeemEstoque;
    }

    public void setMenorValidadeemEstoque(Calendar menorValidadeemEstoque) {
        this.menorValidadeemEstoque = menorValidadeemEstoque;
    }

    public double getLucro(){
        return valordeVenda - valordeCompra;
    }

}
