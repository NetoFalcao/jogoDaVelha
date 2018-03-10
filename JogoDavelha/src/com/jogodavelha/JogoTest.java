package com.jogodavelha;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JogoTest {

	private Jogo jogo;
	
	@Before
	public void novoJogo() {
		jogo = new Jogo();
	}
	
	@Test
	public void criarJogo() {
		assertFalse("O jogo iniciou finalizado",jogo.acabou());
		
	}
	
	@Test
	public void definirPrimeiroJogador() {
		jogo.setMarcaPrimeiroJogadorX(true);
		assertTrue(jogo.isMarcaPrimeiroJogadorX());
	}
	
	@Test
	public void definirPrimeiroJogadorDenovo() {
		jogo.setMarcaPrimeiroJogadorX(true); //X
		jogo.setMarcaPrimeiroJogadorX(false); //O
		assertFalse(jogo.isMarcaPrimeiroJogadorX()); //O
	}
	
	@Test
	public void desenharPrimeiraMarca() {
		jogo.setMarcaPrimeiroJogadorX(true);
		jogo.desenhaMarca(1,0);
		assertTrue(jogo.isMarcaXNaPosicao(1,0));
	}
	
	@Test(expected=ExcecaoJogoDaVelha.class)
	public void desenharEmcCelulaOcupada() {
		jogo.setMarcaPrimeiroJogadorX(true);
		jogo.desenhaMarca(1,0);
		jogo.desenhaMarca(1,0);
	}
	
	@Test(expected=ExcecaoJogoDaVelha.class)
	public void desenharEmcColunaErrada() {
		jogo.setMarcaPrimeiroJogadorX(false); //O
		jogo.desenhaMarca(1, 4);
	}
	
	@Test(expected=ExcecaoJogoDaVelha.class)
	public void desenharEmLinhaErrada() {
		jogo.setMarcaPrimeiroJogadorX(false); //O
		jogo.desenhaMarca(-1, 0);
	}
	
	@Test
	public void lerDeUmaCelulaDesocupada() {
		jogo.setMarcaPrimeiroJogadorX(false); //O
		assertNull(jogo.isMarcaXNaPosicao(0, 0));
	}

	@Test (expected=ExcecaoJogoDaVelha.class)
	public void lerMarcaDeUmaColunaErrada() {
		jogo.setMarcaPrimeiroJogadorX(true); //O
		jogo.isMarcaXNaPosicao(1, 3);
	}
	
	@Test (expected=ExcecaoJogoDaVelha.class)
	public void lerMarcaDeUmaLinhaErrada() {
		jogo.setMarcaPrimeiroJogadorX(true); //O
		jogo.isMarcaXNaPosicao(-1, 1);
	}
	
	@Test (expected=ExcecaoJogoDaVelha.class)
	public void definirPrimeiroJogadorAposInicioDoJogo() {
		jogo.setMarcaPrimeiroJogadorX(true); //O
		jogo.desenhaMarca(1, 0); //inicio Do JOgo
		jogo.setMarcaPrimeiroJogadorX(true);
	}
	
	@Test (expected=ExcecaoJogoDaVelha.class)
	public void desenharMarcaAntesDeDefinirPrimeiroJogadorAposInicioDoJogo() {
		jogo.desenhaMarca(1, 0);
	}
	
	@Test
	public void desenharSegundaMarca() {
		jogo.setMarcaPrimeiroJogadorX(true);
		jogo.desenhaMarca(1,0); // x
		jogo.desenhaMarca(1, 1); // o
		assertFalse(jogo.isMarcaXNaPosicao(1, 1));
	}
	
	@Test
	public void jogoGanhoAtravesDeColuna() {
		jogo.setMarcaPrimeiroJogadorX(false);
		jogo.desenhaMarca(0, 0); 
		jogo.desenhaMarca(1, 2);
		jogo.desenhaMarca(1, 0);
		jogo.desenhaMarca(2, 2);
		jogo.desenhaMarca(2, 0);
		assertTrue("esperava que o jogo tivess acabado", jogo.acabou());
	}
	
	@Test
	public void jogoGanhoAtravesDeLinha() {
		jogo.setMarcaPrimeiroJogadorX(false);
		jogo.desenhaMarca(1, 0); 
		jogo.desenhaMarca(0, 2);
		jogo.desenhaMarca(1, 2);
		jogo.desenhaMarca(2, 0);
		jogo.desenhaMarca(1, 1);
		assertTrue("esperava que o jogo tivess acabado", jogo.acabou());
	}
	

	@Test
	public void jogoGanhoAtravesDeDiagonal() {
		jogo.setMarcaPrimeiroJogadorX(false);
		jogo.desenhaMarca(0, 2); 
		jogo.desenhaMarca(0, 0);
		jogo.desenhaMarca(1, 1);
		jogo.desenhaMarca(2, 2);
		jogo.desenhaMarca(2, 0);
		assertTrue("esperava que o jogo tivess acabado", jogo.acabou());
	}
	
	@Test (expected=ExcecaoJogoDaVelha.class)
	public void desenharMarcaAposJogoGanho() {
		jogo.setMarcaPrimeiroJogadorX(false);
		jogo.desenhaMarca(0, 2); 
		jogo.desenhaMarca(0, 0);
		jogo.desenhaMarca(1, 1);
		jogo.desenhaMarca(2, 2);
		jogo.desenhaMarca(2, 0);
		jogo.desenhaMarca(2, 1);
	}
}