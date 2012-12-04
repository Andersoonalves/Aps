package teste;

import static org.junit.Assert.*;
import org.junit.*;
import estoque.*;

public class TesteEstoque {
	private FaixadaEstoque es;
	private Produto p;
	private Produto p2;
	private Promocao pm;
	private Promocao pm2;
	
	@Before
	public void iniciarEstoqueProduto(){
		es = new FaixadaEstoque();
		p = new Produto();
		p2 = new Produto();
		pm = new Promocao();
		pm2 = new Promocao();
	}
	@Test
	public void procurarProdutoComAListaVazia() {
		assertNull("O produto deveriar ser Null",es.getProduto("Qualquer_Produto"));
	}
	@Test
	public void criarProduto() throws ExeptionFachadaEstoque {
		setProduto(p,"Feijão","123");
		assertTrue("O produto deveriar ser criado normalmente",es.criar(p));
	}
	@Test
	public void criarProdutoComMesmoNome() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		setProduto(p2,"Feijão","321");
		es.criar(p);
		assertFalse("O produto deveriar não poder ser criado por que já existe",es.criar(p2));
	}
	@Test
	public void criarProdutoComMesmoCodigo() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		setProduto(p2,"Arroz","123");
		es.criar(p);
		assertFalse("O produto deveriar não poder ser criado por que já existe",es.criar(p2));
	}
	@Test
	public void criar2Produto() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		setProduto(p2,"Arroz","321");
		es.criar(p);
		assertTrue("O produto deveriar poder ser criado",es.criar(p2));
	}
	@Test
	public void criarERecuperarProdutoPeloNome() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertEquals("Os produtos deveriam ser iguais",p,es.getProduto("Feijão"));
	}
	@Test
	public void criarERecuperarProdutoPeloCodigo() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertEquals("Os produtos deveriam ser iguais",p,es.getProduto("123"));
	}
	@Test
	public void procurarProdutoInexistente() throws ExeptionFachadaEstoque{
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
	public void alterarNomeDoProdutoEProcurar()throws ExeptionFachadaEstoque {
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setNome("Queijo");
		assertEquals("O produto deveria ser encontrado",p,es.getProduto("Queijo"));
	}
	@Test
	public void alterarCodigoDoProdutoEProcurar() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setCodigo("321");
		assertEquals("O produto deveria ser encontrado",p,es.getProduto("321"));
	}
	@Test
	public void removerProduto() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertTrue("O produto deveria ser removido",es.remover(p));
	}
	@Test
	public void procurarProdutoRemovido() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		es.remover(p);
		assertNull("O produto deveria não existir poque já foi removido",es.getProduto("Queijo"));
	}
	@Test
	public void removerProdutoDepoisDeAlterarONomeEOCodigo() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setNome("Queijo");
		p.setCodigo("321");
		assertTrue("O produto deveria ser removido",es.remover(p));
	}
	@Test
	public void setFornecedorDoProduto() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertTrue("Deveria poder ser setado o fornecedor do produto",es.setFornecedor("Tropeiro", p.getNome()));
	}
	@Test
	public void zerarQuantidadeVendidoDosProdutos() throws ExeptionFachadaEstoque {
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
	public void zerarQuantidadeNegativaDosProdutos() throws ExeptionFachadaEstoque{
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
	public void zerarQuantidadeNegativaDeUmProduto() throws ExeptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setQuantidadeemEstoque(-7);
		assertEquals("Deveria ter zerado a quantidadeemEstoque dos produtos",1,es.zerarQuantidadeNegativa());
	}
	public void setProduto(Produto p, String nome, String codigo){
		p.setNome(nome);
		p.setCodigo(codigo);
	}
	
	@Test(expected = ExeptionFachadaEstoque.class)
	public void criarProdutoSemCodigo() throws ExeptionFachadaEstoque{
		p.setNome("Feijão");
		es.criar(p);
	}
	
	@Test(expected = ExeptionFachadaEstoque.class)
	public void criarProdutoSemNome() throws ExeptionFachadaEstoque{
		p.setCodigo("123");
		es.criar(p);
	}
	@Test(expected = ExeptionFachadaEstoque.class)
	public void criarProdutoSemNomeESemCodigo() throws ExeptionFachadaEstoque{
		es.criar(p);
	}
	
}
