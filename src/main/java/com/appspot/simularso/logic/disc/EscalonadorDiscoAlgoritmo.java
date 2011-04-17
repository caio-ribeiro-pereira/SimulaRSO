package com.appspot.simularso.logic.disc;

public enum EscalonadorDiscoAlgoritmo {
		FCFS("FC-FS"), SSTF("SSTF"), CSCAN("CSCAN"), SCAN("SCAN"), LOOK("LOOK");

		private String nome;

		EscalonadorDiscoAlgoritmo(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return this.nome;
		}
	}
