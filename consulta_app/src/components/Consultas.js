import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Consultas = () => {
  const [consultas, setConsultas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchConsultas = async () => {
      try {
        const response = await axios.get('http://localhost:8081/');
        setConsultas(response.data);
        setLoading(false);
      } catch (err) {
        setError(err);
        setLoading(false);
      }
    };

    fetchConsultas();
  }, []);

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div>
      <h1>Consultas</h1>
      <ul>
        {consultas.map((consulta) => (
          <li key={consulta.id}>
            <p>ID: {consulta.id}</p>
            <p>Paciente: {consulta.paciente}</p>
            <p>Data: {consulta.data}</p>
            <p>Consulta Finalizada: {consulta.consultaFinalizada ? 'Sim' : 'Não'}</p>
            <p>Diagnóstico: {consulta.diagnostico ? consulta.diagnostico : 'Ainda não diagnosticado'}</p>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Consultas;
