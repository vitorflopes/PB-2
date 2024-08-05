-- Insert data into Usuario table
INSERT INTO usuario (id, nome, email, senha, is_doctor, especialidade) VALUES
(1, 'Igor', 'igor@gmail.com', '123', false, 'Clinico Drone'),
(2, 'Dr. House', 'house@gmail.com', '123', true, 'Diagnostician'),
(3, 'Dr. Smith', 'smith@gmail.com', '123', true, 'Cardiologist');

-- Insert data into Consulta table
INSERT INTO consultas (id, data, id_paciente, id_medico, consulta_finalizada, diagnostico) VALUES
(2, '2023-12-12', 1, 2, false, '3 dias de vida'),
(3, '2023-12-13', 1, 3, true, 'Consulta de retorno'),
(4, '2023-12-14', 2, 3, false, 'Consulta de rotina');
