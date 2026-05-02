package com.krakadev.financiero.servicios;

import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;

public class Banco {
	
	private int ultimoCodigo = 1000;
	
	public Banco() {
		
	}

	public int getUltimoCodigo() {
		return ultimoCodigo;
	}

	public void setUltimoCodigo(int ultimoCodigo) {
		this.ultimoCodigo = ultimoCodigo;
	}
	
	// metodo crearCuenta
	public Cuenta crearCuenta(Cliente cliente) {
		String codigoStr = ultimoCodigo + "";
		ultimoCodigo ++;
		
		Cuenta cuentaNueva = new Cuenta(codigoStr);
		cuentaNueva.setPropietario(cliente);
		
		return cuentaNueva;
	}
	
	//metodo depositar
	public boolean depositar (double monto, Cuenta cuenta) {
		if (monto > 0 ) {
			cuenta.setSaldoActual(cuenta.getSaldoActual() + monto);
			return true;	
		}else {
			return false;
		}	
	}
	
	//metodo retirar
	public boolean retirar(double monto, Cuenta cuenta) {
		if(monto > 0 && monto <= cuenta.getSaldoActual()) {
			cuenta.setSaldoActual(cuenta.getSaldoActual() - monto);
			return true;
		}else {
			return false;
		}
	}
	
	//metodo transferir
	public boolean transferir(Cuenta origen,Cuenta destino,double monto) {
		boolean retiro = retirar(monto,origen);
		
		if(retiro) {
			depositar(monto,destino);
			return true;
		}else{
			return false;
		}
	}

}
