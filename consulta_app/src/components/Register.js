import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import {
    Box,
    Button,
    FormControl,
    FormLabel,
    Input,
    Heading,
    VStack,
    useToast,
    HStack,
    Select,
} from '@chakra-ui/react';
import wave from '../wave/wave.svg';

// Importa o JSON das especialidades
import specialtiesData from '../especialidades.json';

const Register = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [isDoctor, setIsDoctor] = useState(false);
    const [specialty, setSpecialty] = useState('');
    const [specialties, setSpecialties] = useState([]);
    const navigate = useNavigate();
    const toast = useToast();

    useEffect(() => {
        setSpecialties(specialtiesData);
    }, []);

    const handleSubmit = (event) => {
        event.preventDefault();

        console.log('Name:', name);
        console.log('Email:', email);
        console.log('Password:', password);
        console.log('Is Doctor:', isDoctor);
        console.log('Specialty:', specialty);

        toast({
            title: "Conta criada com sucesso!",
            status: "success",
            duration: 3000,
            isClosable: true,
        });

        navigate('/consultas');
    };

    return (
        <Box
            d="flex"
            justifyContent="center"
            alignItems="center"
            height="100vh"
            p={4}
            position="relative"
            _after={{
                content: `""`,
                backgroundImage: `url(${wave})`,
                backgroundRepeat: "no-repeat",
                backgroundPosition: "bottom",
                backgroundSize: "contain",
                position: "absolute",
                bottom: -40,
                left: 0,
                right: 0,
                height: "100%",
                zIndex: -1,
            }}
        >
            <HStack spacing={10} justifyContent="center" alignItems="center" mt={35}>
                <Box
                    bg="white"
                    p={6}
                    rounded="md"
                    shadow="md"
                    width="100%"
                    maxWidth="400px"
                >
                    <VStack spacing={4} as="form" onSubmit={handleSubmit}>
                        <Heading as="h2" size="lg" textAlign="center">Criar conta</Heading>
                        <FormControl id="name" isRequired>
                            <FormLabel>Nome</FormLabel>
                            <Input
                                type="text"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        </FormControl>
                        <FormControl id="email" isRequired>
                            <FormLabel>Email</FormLabel>
                            <Input
                                type="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                        </FormControl>
                        <FormControl id="password" isRequired>
                            <FormLabel>Senha</FormLabel>
                            <Input
                                type="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                        </FormControl>
                        <FormControl id="userType" isRequired>
                            <FormLabel>Tipo de usuário</FormLabel>
                            <Select
                                value={isDoctor ? 'doctor' : 'patient'}
                                onChange={(e) => {
                                    setIsDoctor(e.target.value === 'doctor');
                                }}
                            >
                                <option value="patient">Paciente</option>
                                <option value="doctor">Médico</option>
                            </Select>
                        </FormControl>
                        {isDoctor && (
                            <FormControl id="specialty" isRequired>
                                <FormLabel>Especialidade</FormLabel>
                                <Select
                                    value={specialty}
                                    onChange={(e) => setSpecialty(e.target.value)}
                                >
                                    <option value="">Selecione a especialidade</option>
                                    {specialties.map((spec, index) => (
                                        <option key={index} value={spec}>
                                            {spec}
                                        </option>
                                    ))}
                                </Select>
                            </FormControl>
                        )}
                        <Button
                            colorScheme="blue"
                            type="submit"
                            width="full"
                        >
                            Criar Conta
                        </Button>
                    </VStack>
                </Box>
            </HStack>
        </Box>
    );
};

export default Register;
