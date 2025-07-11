package com.unsa.cooperativa.ui;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.unsa.cooperativa.dao.CooperativaDAO;
import com.unsa.cooperativa.dao.CooperativaDAOImpl;
import com.unsa.cooperativa.dao.DepartamentoDAO;
import com.unsa.cooperativa.dao.DepartamentoDAOImpl;
import com.unsa.cooperativa.dao.DistritoDAO;
import com.unsa.cooperativa.dao.DistritoDAOImpl;
import com.unsa.cooperativa.dao.ProductoDAO;
import com.unsa.cooperativa.dao.ProductoDAOImpl;
import com.unsa.cooperativa.dao.ProvinciaDAO;
import com.unsa.cooperativa.dao.ProvinciaDAOImpl;
import com.unsa.cooperativa.dao.RolDAO;
import com.unsa.cooperativa.dao.RolDAOImpl;
import com.unsa.cooperativa.dao.TasaDAO;
import com.unsa.cooperativa.dao.TasaDAOImpl;
import com.unsa.cooperativa.dao.TipoSocioDAO;
import com.unsa.cooperativa.dao.TipoSocioDAOImpl;
import com.unsa.cooperativa.dao.UsuarioDAO;
import com.unsa.cooperativa.dao.UsuarioDAOImpl;
import com.unsa.cooperativa.service.CooperativaService;
import com.unsa.cooperativa.service.CooperativaServiceImpl;
import com.unsa.cooperativa.service.DepartamentoService;
import com.unsa.cooperativa.service.DepartamentoServiceImpl;
import com.unsa.cooperativa.service.DistritoService;
import com.unsa.cooperativa.service.DistritoServiceImpl;
import com.unsa.cooperativa.service.ProductoService;
import com.unsa.cooperativa.service.ProductoServiceImpl;
import com.unsa.cooperativa.service.ProvinciaService;
import com.unsa.cooperativa.service.ProvinciaServiceImpl;
import com.unsa.cooperativa.service.RolService;
import com.unsa.cooperativa.service.RolServiceImpl;
import com.unsa.cooperativa.service.TasaService;
import com.unsa.cooperativa.service.TasaServiceImpl;
import com.unsa.cooperativa.service.TipoSocioService;
import com.unsa.cooperativa.service.TipoSocioServiceImpl;
import com.unsa.cooperativa.service.UsuarioService;
import com.unsa.cooperativa.service.UsuarioServiceImpl;

public class MasterTablesPanel extends JPanel {
  public MasterTablesPanel(Connection connection) {
    setLayout(new BorderLayout());

    // Initialize DAOs and Services
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(connection);
    UsuarioService usuarioService = new UsuarioServiceImpl(usuarioDAO);
    RolDAO rolDAO = new RolDAOImpl(connection);
    RolService rolService = new RolServiceImpl(rolDAO);
    CooperativaDAO cooperativaDAO = new CooperativaDAOImpl(connection);
    CooperativaService cooperativaService = new CooperativaServiceImpl(cooperativaDAO);
    DepartamentoDAO departamentoDAO = new DepartamentoDAOImpl(connection);
    DepartamentoService departamentoService = new DepartamentoServiceImpl(departamentoDAO);
    ProvinciaDAO provinciaDAO = new ProvinciaDAOImpl(connection);
    ProvinciaService provinciaService = new ProvinciaServiceImpl(provinciaDAO);
    DistritoDAO distritoDAO = new DistritoDAOImpl(connection);
    DistritoService distritoService = new DistritoServiceImpl(distritoDAO);
    TipoSocioDAO tipoSocioDAO = new TipoSocioDAOImpl(connection);
    TipoSocioService tipoSocioService = new TipoSocioServiceImpl(tipoSocioDAO);
    TasaDAO tasaDAO = new TasaDAOImpl(connection);
    TasaService tasaService = new TasaServiceImpl(tasaDAO);
    ProductoDAO productoDAO = new ProductoDAOImpl(connection);
    ProductoService productoService = new ProductoServiceImpl(productoDAO);

    // Create tabbed pane for master tables
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Usuarios", new UsuarioPanel(usuarioService));
    tabbedPane.addTab("Roles", new RolPanel(rolService));
    tabbedPane.addTab("Cooperativas", new CooperativaPanel(cooperativaService, usuarioService));
    tabbedPane.addTab("Departamentos", new DepartamentoPanel(departamentoService));
    tabbedPane.addTab("Provincias", new ProvinciaPanel(provinciaService, departamentoService));
    tabbedPane.addTab("Distritos", new DistritoPanel(distritoService, provinciaService));
    tabbedPane.addTab("Tipos de Socio", new TipoSocioPanel(tipoSocioService));
    tabbedPane.addTab("Tasas", new TasaPanel(tasaService));
    tabbedPane.addTab("Productos", new ProductoPanel(productoService, tipoSocioService));

    add(tabbedPane, BorderLayout.CENTER);
  }
}