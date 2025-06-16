package org.example.presentation;

import org.example.cart.ShoppingCartService;
import org.example.exception.NoSuchProductException;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.slf4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

class CommandProcessorTest {

    @Mock
    ShoppingCartService shoppingCartService;

    @InjectMocks
    CommandProcessor commandProcessor;

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayInputStream testIn;
    private TestPrintStream testOut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testOut = new TestPrintStream();
        System.setOut(testOut);
    }

    @AfterEach
    void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    void testProcessExitImmediately() {
        provideInput("exit\n");
        commandProcessor.process();
        Assertions.assertTrue(testOut.getOutput().contains("Exiting program..."));
        verifyNoInteractions(shoppingCartService);
    }

    @Test
    void testAddProductSuccess() throws NoSuchProductException {
        provideInput("add product Guitar 2\nexit\n");
        // Mock hogy ne dobjon
        doNothing().when(shoppingCartService).addProduct("Guitar", 2);

        commandProcessor.process();

        Assertions.assertTrue(testOut.getOutput().contains("Product added: Guitar"));
        verify(shoppingCartService).addProduct("Guitar", 2);
    }

    @Test
    void testAddProductNotFound() throws NoSuchProductException {
        provideInput("add product NonExistent 5\nexit\n");
        doThrow(new NoSuchProductException()).when(shoppingCartService).addProduct("NonExistent", 5);

        commandProcessor.process();

        Assertions.assertTrue(testOut.getOutput().contains("Product not found: NonExistent"));
        verify(shoppingCartService).addProduct("NonExistent", 5);
    }

    @Test
    void testOrderProducts() {
        provideInput("order products\nexit\n");
        doNothing().when(shoppingCartService).order();

        commandProcessor.process();

        Assertions.assertTrue(testOut.getOutput().contains("Products ordered successfully."));
        verify(shoppingCartService).order();
    }

    @Test
    void testListProducts() {
        provideInput("list products\nexit\n");
        doNothing().when(shoppingCartService).listProducts();

        commandProcessor.process();

        verify(shoppingCartService).listProducts();
    }

    @Test
    void testUnknownCommand() {
        provideInput("foobar\nexit\n");
        commandProcessor.process();
        Assertions.assertTrue(testOut.getOutput().contains("Unknown command. Try again."));
    }

    // Helper metódus a bemenet átállításához
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    // Egyszerű osztály a standard output fogására
    private static class TestPrintStream extends PrintStream {
        private final StringBuilder output = new StringBuilder();

        public TestPrintStream() {
            super(System.out);
        }

        @Override
        public void println(String x) {
            output.append(x).append("\n");
            super.println(x);
        }

        @Override
        public void print(String s) {
            output.append(s);
            super.print(s);
        }

        public String getOutput() {
            return output.toString();
        }
    }
}