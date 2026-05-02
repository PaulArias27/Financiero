package com.krakedev.financiero.testEntidades;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakadev.financiero.servicios.Banco;
import com.krakedev.financiero.entidades.Cliente;
import com.krakedev.financiero.entidades.Cuenta;


public class BancoTestJUnit {
	
	 private Banco banco;
	 private Cliente cliente1;
	 private Cliente cliente2;
	 private Cuenta cuenta1;
	 private Cuenta cuenta2;

	 @BeforeEach
	 public void setUp() {
		 banco = new Banco();

	     cliente1 = new Cliente("0101", "Juan", "Perez");
	     cliente2 = new Cliente("0202", "Maria", "Lopez");

	     cuenta1 = banco.crearCuenta(cliente1);
	     cuenta2 = banco.crearCuenta(cliente2);
	 }
	 // =========================
	    // PRUEBAS crearCuenta()
	    // =========================

	    @Test
	    public void testCrearCuentaCodigoConsecutivo() {
	        assertEquals("1000", cuenta1.getId());
	        assertEquals("1001", cuenta2.getId());
	    }

	    @Test
	    public void testCrearCuentaPropietarioCorrecto() {
	        assertEquals("0101", cuenta1.getPropietario().getCedula());
	        assertEquals("Juan", cuenta1.getPropietario().getNombre());
	    }
	    // =========================
	    // PRUEBAS depositar()
	    // =========================

	    @Test
	    public void testDepositarExitoso() {
	        boolean resultado = banco.depositar(500, cuenta1);

	        assertTrue(resultado);
	        assertEquals(500, cuenta1.getSaldoActual());
	    }

	    @Test
	    public void testDepositarMontoInvalido() {
	        boolean resultado = banco.depositar(-100, cuenta1);

	        assertFalse(resultado);
	        assertEquals(0, cuenta1.getSaldoActual());
	    }
	 // =========================
	    // PRUEBAS retirar()
	    // =========================

	    @Test
	    public void testRetirarExitoso() {
	        banco.depositar(1000, cuenta1);

	        boolean resultado = banco.retirar(300, cuenta1);

	        assertTrue(resultado);
	        assertEquals(700, cuenta1.getSaldoActual());
	    }

	    @Test
	    public void testRetirarSaldoInsuficiente() {
	        banco.depositar(200, cuenta1);

	        boolean resultado = banco.retirar(500, cuenta1);

	        assertFalse(resultado);
	        assertEquals(200, cuenta1.getSaldoActual());
	    }

	    @Test
	    public void testRetirarMontoInvalido() {
	        banco.depositar(500, cuenta1);

	        boolean resultado = banco.retirar(-50, cuenta1);

	        assertFalse(resultado);
	        assertEquals(500, cuenta1.getSaldoActual());
	    }
	 // =========================
	    // PRUEBAS transferir()
	    // =========================

	    @Test
	    public void testTransferirExitoso() {
	        banco.depositar(1000, cuenta1);

	        boolean resultado = banco.transferir(cuenta1, cuenta2, 400);

	        assertTrue(resultado);
	        assertEquals(600, cuenta1.getSaldoActual());
	        assertEquals(400, cuenta2.getSaldoActual());
	    }

	    @Test
	    public void testTransferirSaldoInsuficiente() {
	        banco.depositar(100, cuenta1);

	        boolean resultado = banco.transferir(cuenta1, cuenta2, 500);

	        assertFalse(resultado);
	        assertEquals(100, cuenta1.getSaldoActual());
	        assertEquals(0, cuenta2.getSaldoActual());
	    }

}
