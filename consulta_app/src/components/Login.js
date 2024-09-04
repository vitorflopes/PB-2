import React, { useState } from 'react';
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
    Stat,
    StatLabel,
    StatNumber,
    StatHelpText,
    StatArrow,
    StatGroup,
} from '@chakra-ui/react';
import Lottie from 'react-lottie';
import animationData from '../lottie/login.json';
import wave from '../wave/wave.svg';

const Login = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();
    const toast = useToast();

    const handleSubmit = (event) => {
        event.preventDefault();

        console.log('Email:', email);
        console.log('Password:', password);

        toast({
            title: "Login realizado com sucesso!",
            status: "success",
            duration: 3000,
            isClosable: true,
        });

        navigate('/consultas');
    };

    // Configurações da animação Lottie
    const defaultOptions = {
        loop: true,
        autoplay: true,
        animationData: animationData,
        rendererSettings: {
            preserveAspectRatio: "xMidYMid slice"
        }
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

                <Box maxW='32rem'>
                    <Heading mb={4}>Bem-vindo ao nosso sistema de agendamento de consultas médicas</Heading>
                    <Button size='lg' colorScheme="blue" mt='24px'>
                        Crie sua conta
                    </Button>
                    <StatGroup mt={10}>
                        <Stat>
                            <StatLabel>Novos usuários</StatLabel>
                            <StatNumber>3,670</StatNumber>
                            <StatHelpText>
                                <StatArrow type='increase' />
                                23.36%
                            </StatHelpText>
                        </Stat>


                    </StatGroup>
                </Box>

                <Box
                    bg="white"
                    p={6}
                    rounded="md"
                    shadow="md"
                    width="100%"
                    maxWidth="400px"
                >
                    <VStack spacing={4} as="form" onSubmit={handleSubmit}>
                        <Heading as="h2" size="lg" textAlign="center">Login</Heading>
                        <FormControl id="email" isRequired>
                            <FormLabel>Email</FormLabel>
                            <Input
                                type="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                        </FormControl>
                        <FormControl id="password" isRequired>
                            <FormLabel>Password</FormLabel>
                            <Input
                                type="password"
                                value={password}
                                onChange={(e) => setPassword(e.target.value)}
                            />
                        </FormControl>
                        <Button
                            colorScheme="blue"
                            type="submit"
                            width="full"
                        >
                            Login
                        </Button>
                    </VStack>
                </Box>
            </HStack>

        </Box>
    );
};

export default Login;
