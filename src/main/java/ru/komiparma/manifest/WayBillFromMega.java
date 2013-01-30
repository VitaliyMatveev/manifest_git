package ru.komiparma.manifest;

import java.util.List;

public class WayBillFromMega {
	//Номер
	private String title;
	//Fedex номер
	private String fedexNumber;
	//комментарии
	private String comments;
	
	//Отправитель
	private String senderFIO;
	private String senderPhone;
	private String senderCompany;
	private String senderCountry;
	private String senderCity;
	private String senderAgent;
	private String senderAddress;

	//Получатель
	private String recipientFIO;
	private String recipientPhone;
	private String recipientCompany;
	private String recipientCountry;
	private String recipientCity;
	private String recipientAgent;
	private String recipientAddress;
	
	//Вес
	private String weight;
	//Объёмный вес
	private String volume;
	//Количество
	private String amount;
	//Тип оплаты
	private String payType;
	//Кто платит
	private String wwPay;
	//Дата создания
	private String wbOpenDate;
	//Особые условия доставки
	private String deliveryConditions;
	//Описание груза
	private String cargoDescription;
	//Тип перевозки(внутрение внешние)
	private String shipingType;
	//цена страховки
	private String insurance;
	//Цена перевозки
	private String transportation;
	//Вид перевозки(прямой / москва)
	private Boolean direckTransport;
	
	private List<HistoryOfWayBillFromMega> history;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getFedexNumber() {
		return fedexNumber;
	}
	public void setFedexNumber(String fedexNumber) {
		this.fedexNumber = fedexNumber;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getSenderFIO() {
		return senderFIO;
	}
	public void setSenderFIO(String senderFIO) {
		this.senderFIO = senderFIO;
	}
	public String getSenderPhone() {
		return senderPhone;
	}
	public void setSenderPhone(String senderPhone) {
		this.senderPhone = senderPhone;
	}
	public String getSenderCompany() {
		return senderCompany;
	}
	public void setSenderCompany(String senderCompany) {
		this.senderCompany = senderCompany;
	}
	public String getSenderCountry() {
		return senderCountry;
	}
	public void setSenderCountry(String senderCountry) {
		this.senderCountry = senderCountry;
	}
	public String getSenderCity() {
		return senderCity;
	}
	public void setSenderCity(String senderCity) {
		this.senderCity = senderCity;
	}
	public String getSenderAgent() {
		return senderAgent;
	}
	public void setSenderAgent(String senderAgent) {
		this.senderAgent = senderAgent;
	}
	public String getSenderAddress() {
		return senderAddress;
	}
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}
	
	public String getRecipientFIO() {
		return recipientFIO;
	}
	public void setRecipientFIO(String recipientFIO) {
		this.recipientFIO = recipientFIO;
	}
	public String getRecipientPhone() {
		return recipientPhone;
	}
	public void setRecipientPhone(String recipientPhone) {
		this.recipientPhone = recipientPhone;
	}
	public String getRecipientCompany() {
		return recipientCompany;
	}
	public void setRecipientCompany(String recipientCompany) {
		this.recipientCompany = recipientCompany;
	}
	public String getRecipientCountry() {
		return recipientCountry;
	}
	public void setRecipientCountry(String recipientCountry) {
		this.recipientCountry = recipientCountry;
	}
	public String getRecipientCity() {
		return recipientCity;
	}
	public void setRecipientCity(String recipientCity) {
		this.recipientCity = recipientCity;
	}
	public String getRecipientAgent() {
		return recipientAgent;
	}
	public void setRecipientAgent(String recipientAgent) {
		this.recipientAgent = recipientAgent;
	}
	public String getRecipientAddress() {
		return recipientAddress;
	}
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	public String getWwPay() {
		return wwPay;
	}
	public void setWwPay(String wwPay) {
		this.wwPay = wwPay;
	}
	public String getWbOpenDate() {
		return wbOpenDate;
	}
	public void setWbOpenDate(String wbOpenDate) {
		this.wbOpenDate = wbOpenDate;
	}

	public String getDeliveryConditions() {
		return deliveryConditions;
	}
	public void setDeliveryConditions(String deliveryConditions) {
		this.deliveryConditions = deliveryConditions;
	}
	public List<HistoryOfWayBillFromMega> getHistory() {
		return history;
	}
	public void setHistory(List<HistoryOfWayBillFromMega> history) {
		this.history = history;
	}
	
	public String getCargoDescription() {
		return cargoDescription;
	}
	public void setCargoDescription(String cargoDescription) {
		this.cargoDescription = cargoDescription;
	}
	public String getShipingType() {
		return shipingType;
	}
	public void setShipingType(String shipingType) {
		this.shipingType = shipingType;
	}
	public String getInsurance() {
		return insurance;
	}
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	public String getTransportation() {
		return transportation;
	}
	public Boolean getDireckTransport() {
		return direckTransport;
	}
	public void setDireckTransport(Boolean direckTransport) {
		this.direckTransport = direckTransport;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	
	
}
