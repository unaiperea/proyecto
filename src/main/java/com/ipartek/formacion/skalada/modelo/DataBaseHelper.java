package com.ipartek.formacion.skalada.modelo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * Clase especializada en trabajar con la Base Datos La usaran los DAOs para
 * abrir y cerrar conexiones
 *
 * @author Curso
 *
 */
public class DataBaseHelper {

	// Parametros configuracion base datos
	static final public String DRIVER = "com.mysql.jdbc.Driver";
	static final public String SERVER = "localhost";
	static final public String DATA_BASE = "eskalada";
	static final public String USER = "root";
	static final public String PASS = "";

	// Conexion
	// private Connection con;

	// Logs
	private static final Logger LOG = Logger.getLogger(DataBaseHelper.class);

	/**
	 * Metodo para realizar la conexion implementa un patron singleton (solo
	 * existira un unico objeto)
	 *
	 * @return {@code Connection} la conexion abierta
	 * @throws Exception
	 */
	public static Connection getConnection() throws Exception {

		/*
		 * Conexion usando DriverManager
		 * 
		 * 
		 * if ( con == null ){ Class.forName(DRIVER); con =
		 * DriverManager.getConnection ("jdbc:mysql://" + SERVER + "/" +
		 * DATA_BASE, USER, PASS); } return con;
		 */

		LOG.info("Entramos en getConnection");
		/* Conexion usando DataSource y PoolConexiones */
		Connection con = null;
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/skaladaDB");
		LOG.info("Después de DataSource. " + ctx.lookup("java:comp/env/jdbc/skaladaDB").toString());
		if (ds == null) {
			LOG.info("DataSource ds=null");
			throw new Exception("Data source no encontrado!");
		} else {
			LOG.info("Obtenemos conexion BBDD.");
			con = ds.getConnection();
			LOG.info("No casca al obtener conexion BBDD.");
		}
		LOG.info("antes de comprobar con");
		if (con == null) {
			LOG.info("Error al establecer conexion.");
			throw new Exception("No se ha podido establecer conexion BBDD");
		}
		LOG.info("fin de getConnection");
		return con;
	}

	/**
	 * Metodo para cerrar la conexion. Cuidado porque al cerrar una conexion con
	 * el metodo .close() no la pone a null
	 *
	 * @return
	 */
	public static boolean closeConnection(Connection con) {
		boolean resul = false;
		try {
			if (con != null) {
				con.close();
				con = null;
				LOG.info("Conexion BBDD cerrada.");
			}
			resul = true;
		} catch (SQLException e) {
			LOG.info("Error al cerrar conexion BBDD.");
			con = null;
			e.printStackTrace();
			resul = false;
		}
		return resul;
	}

	/**
	 * Crea la Base Datos ejecutando un Script
	 *
	 * @return {@code Boolean}
	 */
	void crear() {

	}

	/**
	 * Elimina la Base Datos con sentencia DROP
	 */
	void eliminar() {

	}

	/**
	 * Crea las tablas necesarias:
	 * <ol>
	 * <li>test</li>
	 * </ol>
	 */
	void crearTablas() {

	}

	/**
	 * Insertar en las tablas un juego de datos para testear
	 */
	void insertarDatosPrueba() {

	}

}