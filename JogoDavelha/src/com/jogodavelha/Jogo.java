package com.jogodavelha;

public class Jogo {

	private Boolean proximaJogadaX;
	private Boolean[][] tabuleiro = new Boolean[3][3];
	private boolean iniciou;

	public boolean acabou() {
		return ganhouColuna() || ganhouLinha() || ganhouDiagonal();
	}

	private boolean ganhouLinha() {
		for (int i = 0; i < 3; i++){
			if (tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2]
					&& tabuleiro[i][1] != null){
				return true;
				}
		}
		
		return false;
	}

	private boolean ganhouColuna() {
		for (int i = 0; i < 3; i++){
			if (tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i]
					&& tabuleiro[1][i] != null){
				return true;
				}
		}
		
		return false;
	}
	
	private boolean ganhouDiagonal() {
		if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]
				&& tabuleiro[1][1] != null){
			return true;
		}
		if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]
				&& tabuleiro[1][1] != null){
			return true;
		}
		
		return false;
	}

	public void setMarcaPrimeiroJogadorX(boolean marcaPrimeiroJogadorX) {
		if (iniciou) {
			lancarExcecao();
		}
		
		this.proximaJogadaX = marcaPrimeiroJogadorX;
		
		
	}

	public boolean isMarcaPrimeiroJogadorX(){
		return proximaJogadaX;
	}

	public void desenhaMarca(int linha, int coluna) {
		verificarLimites(linha, coluna);
		
		if (tabuleiro[linha][coluna] != null){
			lancarExcecao();
		} 
		if (proximaJogadaX == null){
			lancarExcecao();
		}
		
		if(acabou()){
			lancarExcecao();
		}
	
		tabuleiro[linha][coluna] = proximaJogadaX;
		proximaJogadaX = !proximaJogadaX;
		iniciou = true;
	}

	private void verificarLimites(int linha, int coluna) {
		if (coluna < 0 || coluna > 2){
			lancarExcecao();
		}
		
		if (linha < 0 || linha > 2){
			lancarExcecao();
		}
	}

	private void lancarExcecao() {
		throw new ExcecaoJogoDaVelha();
	}

	public Boolean isMarcaXNaPosicao(int linha, int coluna) {
		verificarLimites(linha, coluna);
		return tabuleiro[linha][coluna];
		
	}

}
