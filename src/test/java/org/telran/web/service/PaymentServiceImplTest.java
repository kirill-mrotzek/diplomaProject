package org.telran.web.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telran.web.entity.Payment;
import org.telran.web.entity.User;
import org.telran.web.enums.OrderStatus;
import org.telran.web.exception.PaymentNotFoundException;
import org.telran.web.repository.PaymentJpaRepository;

/**
 *   Key Features:
 * - Uses `@ExtendWith(MockitoExtension.class)` for Mockito-based testing.
 * - Mocks `PaymentJpaRepository` to isolate business logic.
 * - Covers scenarios like **successful retrieval, not found scenarios, and deletion handling**.
 * - Ensures **proper exception handling for missing payments**.
 */

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private PaymentJpaRepository paymentJpaRepository;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    /**
     **Test Case:** Successfully retrieve a payment by ID.
     **Expected Result:** Returns the correct payment details.
     */
    @Test
    public void testGetByIdPayment_Success() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setAmount(500L);

        User user = new User();
        user.setId(10L);
        payment.setUser(user);

        payment.setDate(LocalDateTime.now());
        payment.setOrderStatus(OrderStatus.CREATED);

        when(paymentJpaRepository.findById(1L)).thenReturn(Optional.of(payment));

        Payment found = paymentService.getByIdPayment(1L);

        assertNotNull(found, "Payment must not be null");
        assertEquals(1L, found.getId(), "ID must be 1");
        assertEquals(500L, found.getAmount(), "Payment amount must be 500");
        assertNotNull(found.getUser(), "User must not be null");
        assertEquals(10L, found.getUser().getId(), "UserID must be 10");
        assertNotNull(found.getDate(), "Payment date must not be null");
        assertEquals(OrderStatus.CREATED, found.getOrderStatus(), "Payment status must be CREATED");
    }

    /**
     **Test Case:** Attempt to retrieve a non-existing payment.
     **Expected Result:** Throws `PaymentNotFoundException`.
     */
    @Test
    public void testGetByIdPayment_NotFound() {
        when(paymentJpaRepository.findById(2L)).thenReturn(Optional.empty());

        PaymentNotFoundException ex = assertThrows(PaymentNotFoundException.class, () -> {
            paymentService.getByIdPayment(2L);
        });
        assertEquals("Payment by ID 2 not found", ex.getMessage());
    }

    /**
     **Test Case:** Successfully create a payment.
     **Expected Result:** The payment is saved and returned correctly.
     */
    @Test
    public void testCreatePayment() {
        Payment payment = new Payment();

        User user = new User();
        user.setId(1L);
        payment.setUser(user);

        when(paymentJpaRepository.save(payment)).thenReturn(payment);

        Payment created = paymentService.createPayment(payment);

        assertNotNull(created, "Payment must not be null");
        assertNotNull(created.getUser(), "User must not be null");
        assertEquals(1L, created.getUser().getId(), "User ID must be 1");
        verify(paymentJpaRepository, times(1)).save(payment);
    }

    /**
     **Test Case:** Successfully delete a payment by ID.
     **Expected Result:** The payment is removed from the repository.
     */
    @Test
    public void testDeletePayment() {
        Long id = 1L;

        doNothing().when(paymentJpaRepository).deleteById(id);

        paymentService.deletePayment(id);

        verify(paymentJpaRepository, times(1)).deleteById(id);
    }
}