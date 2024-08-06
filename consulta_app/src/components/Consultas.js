import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './Consultas.css';

const Consultas = () => {
  const [consultas, setConsultas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [searchTerm, setSearchTerm] = useState('');
  const [showPopup, setShowPopup] = useState(false);
  const [selectedConsulta, setSelectedConsulta] = useState(null);

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

  const handleSearchChange = (event) => {
    setSearchTerm(event.target.value);
  };

  const filteredConsultas = consultas.filter((consulta) =>
    consulta.paciente.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const handleDeleteClick = (consulta) => {
    setSelectedConsulta(consulta);
    setShowPopup(true);
  };

  const handleDeleteConfirm = async () => {
    try {
      await axios.delete(`http://localhost:8081/${selectedConsulta.id}`);
      setConsultas(consultas.filter((consulta) => consulta.id !== selectedConsulta.id));
      setShowPopup(false);
    } catch (error) {
      console.error('Erro ao deletar consulta:', error);
    }
  };

  const handleDeleteCancel = () => {
    setShowPopup(false);
    setSelectedConsulta(null);
  };

  if (loading) return <p>Loading...</p>;
  if (error) return <p>Error: {error.message}</p>;

  return (
    <div className="consultas-container">
      <h1 className="consultas-title">Consultas</h1>
      <input
        type="text"
        placeholder="Buscar por nome do paciente"
        value={searchTerm}
        onChange={handleSearchChange}
        className="search-input"
      />
      <ul className="consultas-list">
        {filteredConsultas.map((consulta) => (
          <li key={consulta.id} className="consulta-item">
            <p>ID: {consulta.id}</p>
            <p>Paciente: {consulta.paciente}</p>
            <p>Data: {consulta.data}</p>
            <p>Consulta Finalizada: {consulta.consultaFinalizada ? 'Sim' : 'Não'}</p>
            <p>Diagnóstico: {consulta.diagnostico ? consulta.diagnostico : 'Ainda não diagnosticado'}</p>
            <button onClick={() => handleDeleteClick(consulta)} className="delete-button">Deletar</button>
          </li>
        ))}
      </ul>

      {showPopup && (
        <div className="popup">
          <div className="popup-content">
            <p>Tem certeza que deseja deletar a consulta de {selectedConsulta.paciente}?</p>
            <button onClick={handleDeleteConfirm} className="confirm-button">Confirmar</button>
            <button onClick={handleDeleteCancel} className="cancel-button">Cancelar</button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Consultas;
