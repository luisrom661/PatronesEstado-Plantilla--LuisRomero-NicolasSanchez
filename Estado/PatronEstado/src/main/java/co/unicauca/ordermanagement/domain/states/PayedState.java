/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.ordermanagement.domain.states;

import co.unicauca.ordermanagement.domain.Order;

/**
 * @author Luis Romero, Jose Nicolas Santander
 */
public class PayedState extends OrderState {
	
	public PayedState(Order order) {
		super(order);
	}
	
	@Override
	public String getStateDescription() {
		return "Pagada";
	}
	
	@Override
	public OrderState orderSendOut(String parcelNumber) {
		boolean v;
		if (!getOrder().isPaymentReceived()) {
			throw new IllegalStateException("An order should not be send out when payment is not received.");
		}
		return new SendState(getOrder());
	}
	
	@Override
	public OrderState cancel() {
		return new CanceledState(getOrder());
	}
}
