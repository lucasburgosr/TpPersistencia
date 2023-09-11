package com.utn.practicopersistencia;

import com.utn.practicopersistencia.entidades.*;
import com.utn.practicopersistencia.enums.Estado;
import com.utn.practicopersistencia.enums.FormaPago;
import com.utn.practicopersistencia.enums.Tipo;
import com.utn.practicopersistencia.enums.TipoEnvio;
import com.utn.practicopersistencia.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class PracticopersistenciaApplication {

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	PedidoRepository pedidoRepository;

	@Autowired
	FacturaRepository facturaRepository;

	@Autowired
	DetallePedidoRepository detallePedidoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@Autowired
	DomicilioRepository domicilioRepository;

	@Autowired
	ProductoRepository productoRepository;

	@Autowired
	RubroRepository rubroRepository;

	public static void main(String[] args) {
		SpringApplication.run(PracticopersistenciaApplication.class, args);

	}

	@Bean
	CommandLineRunner init(ClienteRepository clienteRepo, PedidoRepository pedidoRepo, FacturaRepository facturaRepo, DetallePedidoRepository detallePedidoRepo, UsuarioRepository usuarioRepo, DomicilioRepository domicilioRepo, ProductoRepository productoRepo, RubroRepository rubroRepo){
		return args ->{
			System.out.println("Estoy funcionando");

			//CREA UN PRODUCTO
			Producto producto = Producto.builder()
					.tipo(Tipo.MANUFACTURADO)
					.tiempoEstimadoCocina(1)
					.denominacion("Hamburguesa Triple")
					.precioVenta(150.00)
					.precioCompra(200.00)
					.stockActual(20)
					.stockMinimo(10)
					.unidadMedida("Gr")
					.foto("foto.jpg")
					.receta("Cocinar las hamburgesas 7 minutos de cada lado. Luego colocar el pan base, lechuga, un medallón, queso, otro medallón, pepino, el tercer medallón y tomate. Por último colocar el último pan.")
					.build();

			//REALIZA LA PERSISTENCIA DEL PRODUCTO
			productoRepo.save(producto);

			//CREA UNA LISTA DE PRODUCTOS Y LE AGREGA EL PRODUCTO CREADO
			List<Producto> productos = new ArrayList<>();
			productos.add(producto);

			//CREA UNA FACTURA
			Factura factura = Factura.builder()
					.fecha(LocalDateTime.now())
					.numero(1)
					.descuento(0.00)
					.formaPago(FormaPago.MERCADOPAGO)
					.total(15000)
					.build();

			//REALIZA LA PERSISTENCIA DE LA FACTURA
			facturaRepo.save(factura);

			//CREA UNA LISTA DE DETALLES DE PEDIDO
			List<DetallePedido> detallePedidos = new ArrayList<>();

			//CREA UN PEDIDO
			Pedido pedido = Pedido.builder()
					.fecha("21/09/2023")
					.estado(Estado.INICIADO)
					.horaEstimadaEntrega(LocalTime.now().plusHours(1))
					.tipoEnvio(TipoEnvio.DELIVERY)
					.total(100.00)
					.detallesPedidos(detallePedidos)
                    .factura(factura)
					.build();

			//REALIZA LA PERSISTENCIA DEL PEDIDO
			pedidoRepo.save(pedido);

			//CREA UNA LISTA DE PEDIDOS Y LE AGREGA EL PEDIDO CREADO
			List<Pedido> pedidos = new ArrayList<>();
			pedidos.add(pedido);

			//CREA UN DETALLE PARA EL PEDIDO
			DetallePedido detallePedido = DetallePedido.builder()
					.cantidad(1)
					.producto(producto)
					.subtotal(150.00)
					.build();

			//TRAE LA LISTA DE DETALLES DE PEDIDO DEL PEDIDO CREADO Y LE AGREGA EL DETALLE DE PEDIDO CREADO
			pedido.getDetallesPedidos().add(detallePedido);

			//REALIZA LA PERSISTENCIA DEL DETALLE DEL PEDIDO
			detallePedidoRepo.save(detallePedido);

			//CREA UN RUBRO
			Rubro rubro = Rubro.builder()
					.productos(productos)
					.denominacion("Gastronomía")
					.build();

			//REALIZA LA PERSISTENCIA DEL RUBRO
			rubroRepo.save(rubro);

			//CREA UN USUARIO
			Usuario usuario = Usuario.builder()
					.nombre("El buen sabor")
					.password("12345")
					.rol("ADMINISTRADOR")
					.pedidos(pedidos)
					.build();

			//REALIZA LA PERSISTENCIA DEL USUARIO
			usuarioRepo.save(usuario);

			//CREA UN CLIENTE
			Cliente cliente = Cliente.builder()
					.nombre("Juan")
					.apellido("Pérez")
					.email("juanperez@gmail.com")
					.telefono("2610000000")
					.pedidos(pedidos)
					.build();

			//REALIZA LA PERSISTENCIA DEL CLIENTE
			clienteRepo.save(cliente);

			//CREA UN DOMICILIO
			Domicilio domicilio = Domicilio.builder()
					.calle("San Martín")
					.numero("2550")
					.localidad("Capital")
					.pedidos(pedidos)
					.cliente(cliente)
					.build();

			//REALIZA LA PERSISTENCIA DEL DOMICILIO
			domicilioRepo.save(domicilio);

			try {

				Optional<Pedido> pedidoOptional = pedidoRepo.findById(pedido.getId());

				if (pedidoOptional.isPresent()){
					System.out.println("PEDIDO 1: ");

					System.out.println("La fecha en que se realizó el pedido es: " + pedidoOptional.get().getFecha());
					System.out.println("El estado del pedido es: " + pedidoOptional.get().getEstado());
					System.out.println("La hora estimada de entrega es: " + pedidoOptional.get().getHoraEstimadaEntrega());
					System.out.println("El tipo de envío es: " + pedidoOptional.get().getTipoEnvio());
					System.out.println("El total del pedido es: $" + pedidoOptional.get().getTotal());
				}

			} catch(Exception e) {
				throw new Exception("Hubo un problema intentando recuperar el pedido de la base de datos");
			}

		};

	}

}
