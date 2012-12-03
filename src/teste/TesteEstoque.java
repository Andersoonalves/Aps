package teste;

import static org.junit.Assert.*;
import org.junit.*;
import estoque.*;

public class TesteEstoque {
	private Estoque es;
	private Produto p;
	private Produto p2;
	@Before
	public void iniciarEstoqueProduto(){
		es = new Estoque();
		p = new Produto();
		p2 = new Produto();
	}
	@Test
	public void procurarProdutoComAListaVazia() {
		assertNull("O produto deveriar ser Null",es.getProduto("Qualquer_Produto"));
	}
	@Test
	public void criarProduto() {
		setProduto(p,"Feijão","123");
		assertTrue("O produto deveriar ser criado normalmente",es.criar(p));
	}
	@Test
	public void criarProdutoComMesmoNome() {
		setProduto(p,"Feijão","123");
		setProduto(p2,"Feijão","321");
		es.criar(p);
		assertFalse("O produto deveriar não poder ser criado por que já existe",es.criar(p2));
	}
	@Test
	public void criarProdutoComMesmoCodigo() {
		setProduto(p,"Feijão","123");
		setProduto(p2,"Arroz","123");
		es.criar(p);
		assertFalse("O produto deveriar não poder ser criado por que já existe",es.criar(p2));
	}
	@Test
	public void criar2Produto() {
		setProduto(p,"Feijão","123");
		setProduto(p2,"Arroz","321");
		es.criar(p);
		assertTrue("O produto deveriar poder ser criado",es.criar(p2));
	}
	@Test
	public void criarERecuperarProdutoPeloNome() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertEquals("Os produtos deveriam ser iguais",p,es.getProduto("Feijão"));
	}
	@Test
	public void criarERecuperarProdutoPeloCodigo() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertEquals("Os produtos deveriam ser iguais",p,es.getProduto("123"));
	}
	@Test
	public void procurarProdutoInexistente() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertNull("O produto deveria não existir",es.getProduto("Queijo"));
	}
	@Test
	public void procurarProdutoNaoAdicionado() {
		setProduto(p,"Feijão","123");
		assertNull("O produto deveria não existir",es.getProduto("Queijo"));
	}
	@Test
	public void alterarNomeDoProdutoEProcurar() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setNome("Queijo");
		assertEquals("O produto deveria ser encontrado",p,es.getProduto("Queijo"));
	}
	@Test
	public void alterarCodigoDoProdutoEProcurar() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setCodigo("321");
		assertEquals("O produto deveria ser encontrado",p,es.getProduto("321"));
	}
	@Test
	public void removerProduto() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertTrue("O produto deveria ser removido",es.remover(p));
	}
	@Test
	public void procurarProdutoRemovido() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		es.remover(p);
		assertNull("O produto deveria não existir poque já foi removido",es.getProduto("Queijo"));
	}
	@Test
	public void removerProdutoDepoisDeAlterarONomeEOCodigo() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setNome("Queijo");
		p.setCodigo("321");
		assertTrue("O produto deveria ser removido",es.remover(p));
	}
	@Test
	public void setFornecedorDoProduto() {
		setProduto(p,"Feijão","123");es.criar(p);
		assertTrue("Deveria poder ser setado o fornecedor do produto",es.setFornecedor("Tropeiro", p.getNome()));
	}
	@Test
	public void zerarQuantidadeVendidoDosProdutos() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setQuantidedeVendido(3);
		setProduto(p2,"Queijo","321");
		es.criar(p2);
		p2.setQuantidedeVendido(5);
		es.zerarQuantidadeVendido();
		assertTrue("Deveria ter zerado a quantidadeVendido dos produtos",(p.getQuantidedeVendido()  == p2.getQuantidedeVendido() && p2.getQuantidedeVendido() == 0));
	}
	@Test
	public void zerarQuantidadeNegativaDosProdutos() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setQuantidadeemEstoque(-2);
		setProduto(p2,"Queijo","321");
		es.criar(p2);
		p2.setQuantidadeemEstoque(-8);
		es.zerarQuantidadeNegativa();
		assertTrue("Deveria ter zerado a quantidadeemEstoque dos produtos",(p.getQuantidadeemEstoque()  == p2.getQuantidadeemEstoque() && p2.getQuantidadeemEstoque() == 0));
	}
	@Test
	public void zerarQuantidadeNegativaDeUmProduto() {
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setQuantidadeemEstoque(-7);
		assertEquals("Deveria ter zerado a quantidadeemEstoque dos produtos",1,es.zerarQuantidadeNegativa());
	}
	public void setProduto(Produto p, String nome, String codigo){
		p.setNome(nome);
		p.setCodigo(codigo);
	}

}
