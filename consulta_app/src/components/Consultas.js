import React, { useState } from "react";
import { Link, Box, Flex, Text, Button, Stack } from "@chakra-ui/react";

const NavBar = (props) => {
    const [isOpen, setIsOpen] = useState(false);
    const [selectedPage, setSelectedPage] = useState('appointments');

    const toggle = () => setIsOpen(!isOpen);
    const handleMenuItemClick = (page) => {
        setSelectedPage(page);
        setIsOpen(false);
    };

    return (
        <Flex direction="column" h="100vh" {...props}>
            <NavBarContainer>
                <Flex align="center" w="100%" justify="space-between">
                    <Box>
                        <Text fontSize="lg" fontWeight="bold">Logotipo Fictício</Text>
                    </Box>
                    <Box display={{ base: "block", md: "none" }}>
                        <MenuToggle toggle={toggle} isOpen={isOpen} />
                    </Box>
                    <Box display={{ base: isOpen ? "block" : "none", md: "block" }}>
                        <MenuLinks onMenuItemClick={handleMenuItemClick} />
                    </Box>
                </Flex>
            </NavBarContainer>
            <Box flex="1" p={8}>
                {selectedPage === 'appointments' && <ConteudoAgendamento />}
                {selectedPage === 'settings' && <ConteudoConfiguracao />}
            </Box>
        </Flex>
    );
};

const CloseIcon = () => (
    <svg width="24" viewBox="0 0 18 18" xmlns="http://www.w3.org/2000/svg">
        <title>Close</title>
        <path
            fill="white"
            d="M9.00023 7.58599L13.9502 2.63599L15.3642 4.04999L10.4142 8.99999L15.3642 13.95L13.9502 15.364L9.00023 10.414L4.05023 15.364L2.63623 13.95L7.58623 8.99999L2.63623 4.04999L4.05023 2.63599L9.00023 7.58599Z"
        />
    </svg>
);

const MenuIcon = () => (
    <svg
        width="24px"
        viewBox="0 0 20 20"
        xmlns="http://www.w3.org/2000/svg"
        fill="white"
    >
        <title>Menu</title>
        <path d="M0 3h20v2H0V3zm0 6h20v2H0V9zm0 6h20v2H0v-2z" />
    </svg>
);

const MenuToggle = ({ toggle, isOpen }) => {
    return (
        <Box display={{ base: "block", md: "none" }} onClick={toggle}>
            {isOpen ? <CloseIcon /> : <MenuIcon />}
        </Box>
    );
};

const MenuItem = ({ children, isLast, onClick, ...rest }) => {
    return (
        <Text
            display="block"
            cursor="pointer"
            onClick={onClick}
            {...rest}
        >
            {children}
        </Text>
    );
};

const MenuLinks = ({ onMenuItemClick }) => {
    return (
        <Box
            display={{ base: "block", md: "block" }}
            flexBasis={{ base: "100%", md: "auto" }}
            position={{ base: "absolute", md: "static" }}
            top={{ base: "60px", md: "auto" }}
            left={{ base: 0, md: "auto" }}
            bg={{ base: "blue.500", md: "transparent" }}
            p={4}
            zIndex={10}
            borderRadius="md"
        >
            <Stack
                spacing={8}
                align="center"
                justify={["center", "space-between", "flex-end", "flex-end"]}
                direction={["column", "row", "row", "row"]}
                pt={[4, 4, 0, 0]}
            >
                <MenuItem onClick={() => onMenuItemClick('appointments')}>Meus Agendamentos</MenuItem>
                <MenuItem onClick={() => onMenuItemClick('settings')}>Configurações</MenuItem>
                <MenuItem isLast onClick={() => alert('Sair')}>
                    <Button
                        size="sm"
                        rounded="md"
                        color="white"
                        bg="red.500"
                        _hover={{ bg: "red.600" }}
                    >
                        Sair
                    </Button>
                </MenuItem>
            </Stack>
        </Box>
    );
};

const ConteudoAgendamento = () => {
    return (
        <Box>
            <Text fontSize="2xl" fontWeight="bold">Meus Agendamentos</Text>
            {/* Adicione o conteúdo dos agendamentos aqui */}
        </Box>
    );
};

const ConteudoConfiguracao = () => {
    return (
        <Box>
            <Text fontSize="2xl" fontWeight="bold">Configurações</Text>
            {/* Adicione o conteúdo das configurações aqui */}
        </Box>
    );
};

const NavBarContainer = ({ children, ...props }) => {
    return (
        <Flex
            as="nav"
            align="center"
            justify="space-between"
            wrap="wrap"
            w="100%"
            mb={8}
            p={3}
            bg="blue.500"
            color="white"
            {...props}
        >
            {children}
        </Flex>
    );
};

export default NavBar;
