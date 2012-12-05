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
	public void procurarPromocaoComListaVazia() {
		assertNull("A promoção deve ser Null",es.getPromocao(123));
	}
	
	@Test(expected = ExceptionFachadaEstoque.class)
	public void criarPromocaoSemProduto() throws ExceptionFachadaEstoque {
		assertNull("A promoção nao pode ser criada",es.criar(pm,"123"));
	}
	
	@Test
	public void criarPromocao() throws ExceptionFachadaEstoque{
		setProduto(p,"Arroz","123");
		es.criar(p);
		setPromocao(pm2, 123);
		assertTrue("Promoca deve ser criada",es.criar(pm2, "123"));
	}
	
	@Test
	public void criarERecuperarPromocaoPeloCodigo() throws ExceptionFachadaEstoque{
		setProduto(p,"Arroz","123");
		es.criar(p);
		setPromocao(pm, 123);
		es.criar(pm,"123");
		assertEquals("As promoções devem ser iguais",pm,es.getPromocao(123));
	}
	
	@Test
	public void procurarPromocaoNaoCriada() {
		assertNull("A promocao deveria não existir",es.getPromocao(321));
	}
	
	@Test
	public void alterarCodigoDePromocaoEProcurar() throws ExceptionFachadaEstoque{
		setProduto(p,"Arroz","123");
		es.criar(p);
		setPromocao(pm, 123);
		es.criar(pm,"123");
		setPromocao(pm, 12345);
		assertEquals("A promocao deveria ser encontrado",pm,es.getPromocao(12345));
	}
	
	@Test
	public void alterarNomeDeProdutoEProcurarPromocao() throws ExceptionFachadaEstoque{
		setProduto(p,"Arroz","123");
		es.criar(p);
		setPromocao(pm, 123);
		es.criar(pm,"123");
		p.setNome("Macarrao");
		assertEquals("A promocao deveria ser encontrado",pm,es.getPromocao("Macarrao"));
	}
	
	@Test
	public void removerPromocao() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		setPromocao(pm2, 909);
		es.criar(pm2,"123");
		assertTrue("A promocao deveria ser removido",es.remover(pm2));
	}
		
	public void setPromocao(Promocao promocao,int id){
		promocao.setId(id);
	}
	
	@Test
	public void procurarProdutoComAListaVazia() {
		assertNull("O produto deveriar ser Null",es.getProduto("Qualquer_Produto"));
	}
	
	@Test
	public void criarProduto() throws ExceptionFachadaEstoque {
		setProduto(p,"Feijão","123");
		assertTrue("O produto deveriar ser criado normalmente",es.criar(p));
	}
	
	@Test
	public void criarProdutoComMesmoNome() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		setProduto(p2,"Feijão","321");
		es.criar(p);
		assertFalse("O produto deveriar não poder ser criado por que já existe",es.criar(p2));
	}
	
	@Test
	public void criarProdutoComMesmoCodigo() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		setProduto(p2,"Arroz","123");
		es.criar(p);
		assertFalse("O produto deveriar não poder ser criado por que já existe",es.criar(p2));
	}
	
	@Test
	public void criar2Produto() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		setProduto(p2,"Arroz","321");
		es.criar(p);
		assertTrue("O produto deveriar poder ser criado",es.criar(p2));
	}
	
	@Test
	public void criarERecuperarProdutoPeloNome() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertEquals("Os produtos deveriam ser iguais",p,es.getProduto("Feijão"));
	}
	
	@Test
	public void criarERecuperarProdutoPeloCodigo() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertEquals("Os produtos deveriam ser iguais",p,es.getProduto("123"));
	}
	
	@Test
	public void procurarProdutoInexistente() throws ExceptionFachadaEstoque{
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
	public void alterarNomeDoProdutoEProcurar()throws ExceptionFachadaEstoque {
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setNome("Queijo");
		assertEquals("O produto deveria ser encontrado",p,es.getProduto("Queijo"));
	}
	
	@Test
	public void alterarCodigoDoProdutoEProcurar() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setCodigo("321");
		assertEquals("O produto deveria ser encontrado",p,es.getProduto("321"));
	}
	
	@Test
	public void removerProduto() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertTrue("O produto deveria ser removido",es.remover(p));
	}
	
	@Test
	public void procurarProdutoRemovido() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		es.remover(p);
		assertNull("O produto deveria não existir poque já foi removido",es.getProduto("Queijo"));
	}
	
	@Test
	public void removerProdutoDepoisDeAlterarONomeEOCodigo() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setNome("Queijo");
		p.setCodigo("321");
		assertTrue("O produto deveria ser removido",es.remover(p));
	}
	
	@Test
	public void setFornecedorDoProduto() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		assertTrue("Deveria poder ser setado o fornecedor do produto",es.setFornecedor("Tropeiro", p.getNome()));
	}
	
	@Test
	public void zerarQuantidadeVendidoDosProdutos() throws ExceptionFachadaEstoque {
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
	public void zerarQuantidadeNegativaDosProdutos() throws ExceptionFachadaEstoque{
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
	public void zerarQuantidadeNegativaDeUmProduto() throws ExceptionFachadaEstoque{
		setProduto(p,"Feijão","123");
		es.criar(p);
		p.setQuantidadeemEstoque(-7);
		assertEquals("Deveria ter zerado a quantidadeemEstoque dos produtos",1,es.zerarQuantidadeNegativa());
	}
	
	public void setProduto(Produto p, String nome, String codigo){
		p.setNome(nome);
		p.setCodigo(codigo);
	}
	
	@Test(expected = ExceptionFachadaEstoque.class)
	public void criarProdutoSemCodigo() throws ExceptionFachadaEstoque{
		p.setNome("Feijão");
		es.criar(p);
	}
	
	@Test(expected = ExceptionFachadaEstoque.class)
	public void criarProdutoSemNome() throws ExceptionFachadaEstoque{
		p.setCodigo("123");
		es.criar(p);
	}
	
	@Test(expected = ExceptionFachadaEstoque.class)
	public void criarProdutoSemNomeESemCodigo() throws ExceptionFachadaEstoque{
		es.criar(p);
	}
	
}
