package com.carlocordova.sistemavacunacion;

import com.carlocordova.sistemavacunacion.entities.Empleado;
import com.carlocordova.sistemavacunacion.entities.Vacuna;
import com.carlocordova.sistemavacunacion.repositories.EmpleadoRepository;
import com.carlocordova.sistemavacunacion.repositories.VacunaRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {
    private static final String[] LISTA_VACUNAS = {
            "Sputnik",
            "AstraZeneca",
            "Pfizer",
            "Jhonson&Jhonson"
    };
    private final VacunaRepository vacunaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final Faker faker;

    public SampleDataLoader(VacunaRepository vacunaRepository, EmpleadoRepository empleadoRepository, Faker faker) {
        this.vacunaRepository = vacunaRepository;
        this.empleadoRepository = empleadoRepository;
        this.faker = faker;
    }

    @Override
    public void run(String... args) throws Exception {
        this.registrarVacunas();
        this.generarEmpleados(50);
    }

    //Registrar la lista de vacunas por defecto al iniciar la aplicacion
    private void registrarVacunas() {
        List<Vacuna> vacunas = Arrays.stream(LISTA_VACUNAS).map(nombre -> {
            Vacuna vacuna = new Vacuna();
            vacuna.setNombre(nombre);
            return vacuna;
        }).collect(Collectors.toList());
        vacunaRepository.saveAll(vacunas);
    }

    //Generar empleados aleatorios
    private void generarEmpleados(int size) {
        List<Empleado> empleados = IntStream.rangeClosed(1, size)
                .mapToObj(i -> new Empleado(
                        String.valueOf(faker.number().numberBetween(1000000000L, 9000000000L)),
                        faker.name().firstName() + " " + faker.name().firstName(),
                        faker.name().lastName() + " " + faker.name().lastName(),
                        faker.internet().emailAddress()
                )).collect(Collectors.toList());

        empleadoRepository.saveAll(empleados);
    }
}
