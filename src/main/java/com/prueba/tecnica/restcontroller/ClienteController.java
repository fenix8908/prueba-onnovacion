package com.prueba.tecnica.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.tecnica.dto.ClienteDto;
import com.prueba.tecnica.dto.MensajeRespuesta;
import com.prueba.tecnica.entity.Clientes;
import com.prueba.tecnica.service.ClienteService;

@RestController
@RequestMapping("/prueba/v1/clientes")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/listar")
	public ResponseEntity<List<Clientes>> listarTodosLosClientes() {
		List<Clientes> listado = clienteService.listarClientes();
		return ResponseEntity.ok(listado);
	}

	@PostMapping("/crear")
	public ResponseEntity<?> guardarCliente(@RequestBody ClienteDto clienteDto) {

		if (clienteService.validarExistenciaRut(clienteDto.getRut())) {
			return new ResponseEntity(new MensajeRespuesta("El Rut ingresado ya existe"), HttpStatus.BAD_REQUEST);
		}
		if (clienteService.validarExistenciaDocumento(clienteDto.getNumeroDocumento())) {
			return new ResponseEntity(new MensajeRespuesta("El documento ingresado ya existe"), HttpStatus.BAD_REQUEST);
		}
		Clientes clientes = new Clientes(clienteDto.getNombre(), clienteDto.getApellido(),
				clienteDto.getNumeroDocumento(), clienteDto.getNumeroDocumento(), clienteDto.getRut(),
				clienteDto.getRazonSocial(), clienteDto.getAnioFundacion(), clienteDto.getTipoCliente());
		clienteService.crearCliente(clientes);
		return new ResponseEntity(new MensajeRespuesta("El cliente a sido creado"), HttpStatus.OK);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<?> editarCliente(@PathVariable("id") Long id,@RequestBody ClienteDto clienteDto) {
		
		if(!clienteService.validarExistenciaPorId(id)) {
			return new ResponseEntity(new MensajeRespuesta("El cliente indicado no existe!!"), HttpStatus.NOT_FOUND);
		}

		if (clienteService.validarExistenciaRut(clienteDto.getRut())) {
			return new ResponseEntity(new MensajeRespuesta("El Rut ingresado ya existe"), HttpStatus.BAD_REQUEST);
		}
		if (clienteService.validarExistenciaDocumento(clienteDto.getNumeroDocumento())) {
			return new ResponseEntity(new MensajeRespuesta("El documento ingresado ya existe"), HttpStatus.BAD_REQUEST);
		}
		
		Clientes clientes = clienteService.buscarClientePorId(id).get();
		clientes.setNombre(clienteDto.getNombre());
		clientes.setApellido(clienteDto.getApellido());
		clientes.setTipoDocumento(clienteDto.getNumeroDocumento());
		clientes.setNumeroDocumento(clienteDto.getNumeroDocumento());
		clientes.setRut( clienteDto.getRut());
		clientes.setRazonSocial(clienteDto.getRazonSocial());
		clientes.setAnioFundacion(clienteDto.getAnioFundacion());
		clientes.setTipoCliente(clienteDto.getTipoCliente());
		clienteService.crearCliente(clientes);
		return new ResponseEntity(new MensajeRespuesta("El cliente a sido Actualizado"), HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?>eliminarCliente(@PathVariable("id")Long id){
		
		if(!clienteService.validarExistenciaPorId(id)) {
			return new ResponseEntity(new MensajeRespuesta("El cliente con id:'"+id+"' no existe"), HttpStatus.NOT_FOUND);
		}
		clienteService.eliminarClientePorId(id);
		return new ResponseEntity(new MensajeRespuesta("El cliente ha sido eliminado!!"), HttpStatus.OK);
		
	}

}
