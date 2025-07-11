package com.unsa.cooperativa.ui;

import java.awt.BorderLayout;
import java.sql.Connection;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.unsa.cooperativa.dao.CooperativaDAO;
import com.unsa.cooperativa.dao.CooperativaDAOImpl;
import com.unsa.cooperativa.dao.DistritoDAO;
import com.unsa.cooperativa.dao.DistritoDAOImpl;
import com.unsa.cooperativa.dao.PersonaDAO;
import com.unsa.cooperativa.dao.PersonaDAOImpl;
import com.unsa.cooperativa.dao.RolDAO;
import com.unsa.cooperativa.dao.RolDAOImpl;
import com.unsa.cooperativa.dao.SocioDAO;
import com.unsa.cooperativa.dao.SocioDAOImpl;
import com.unsa.cooperativa.dao.UsuarioDAO;
import com.unsa.cooperativa.dao.UsuarioDAOImpl;
import com.unsa.cooperativa.dao.UsuarioRolDAO;
import com.unsa.cooperativa.dao.UsuarioRolDAOImpl;
import com.unsa.cooperativa.service.CooperativaService;
import com.unsa.cooperativa.service.CooperativaServiceImpl;
import com.unsa.cooperativa.service.DistritoService;
import com.unsa.cooperativa.service.DistritoServiceImpl;
import com.unsa.cooperativa.service.PersonaService;
import com.unsa.cooperativa.service.PersonaServiceImpl;
import com.unsa.cooperativa.service.RolService;
import com.unsa.cooperativa.service.RolServiceImpl;
import com.unsa.cooperativa.service.SocioService;
import com.unsa.cooperativa.service.SocioServiceImpl;
import com.unsa.cooperativa.service.UsuarioRolService;
import com.unsa.cooperativa.service.UsuarioRolServiceImpl;
import com.unsa.cooperativa.service.UsuarioService;
import com.unsa.cooperativa.service.UsuarioServiceImpl;

public class MaintenancePanel extends JPanel {
  public MaintenancePanel(Connection connection) {
    setLayout(new BorderLayout());

    // Initialize DAOs and Services
    RolDAO rolDAO = new RolDAOImpl(connection);
    RolService rolService = new RolServiceImpl(rolDAO);
    UsuarioRolDAO usuarioRolDAO = new UsuarioRolDAOImpl(connection);
    UsuarioRolService usuarioRolService = new UsuarioRolServiceImpl(usuarioRolDAO);
    PersonaDAO personaDAO = new PersonaDAOImpl(connection);
    PersonaService personaService = new PersonaServiceImpl(personaDAO);
    SocioDAO socioDAO = new SocioDAOImpl(connection);
    SocioService socioService = new SocioServiceImpl(socioDAO);
    UsuarioDAO usuarioDAO = new UsuarioDAOImpl(connection);
    UsuarioService usuarioService = new UsuarioServiceImpl(usuarioDAO);
    CooperativaDAO cooperativaDAO = new CooperativaDAOImpl(connection);
    CooperativaService cooperativaService = new CooperativaServiceImpl(cooperativaDAO);
    DistritoDAO distritoDAO = new DistritoDAOImpl(connection);
    DistritoService distritoService = new DistritoServiceImpl(distritoDAO);

    // Create tabbed pane for referential tables
    JTabbedPane tabbedPane = new JTabbedPane();
    tabbedPane.addTab("Usuario-Rol", new UsuarioRolPanel(usuarioRolService, usuarioService, rolService));
    tabbedPane.addTab("Personas", new PersonaPanel(personaService, cooperativaService));
    tabbedPane.addTab("Socios", new SocioPanel(socioService, cooperativaService, distritoService));

    add(tabbedPane, BorderLayout.CENTER);
  }
}