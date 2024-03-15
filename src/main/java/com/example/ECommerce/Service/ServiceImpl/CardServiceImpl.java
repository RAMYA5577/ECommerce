package com.example.ECommerce.Service.ServiceImpl;

import com.example.ECommerce.Dto.RequestDto.CardRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CardResponseDto;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Model.Card;
import com.example.ECommerce.Model.Customer;
import com.example.ECommerce.Repository.CardRepository;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Service.CardService;
import com.example.ECommerce.Service.CustomerService;
import com.example.ECommerce.Transformer.CardTransformer;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

   @Autowired
    CustomerRepository customerRepository;


    @Override
    public CardResponseDto addcard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        Customer customer=customerRepository.findByEmailId(cardRequestDto.getCustomerMailId());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid EmailId!!");
        }
        //dto to entity
        Card card= CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        //save
        Customer savedCustomer=customerRepository.save(customer);

        //entity to dto
        CardResponseDto cardResponseDto=CardTransformer.cardToCardResponseDto(card);
        return cardResponseDto;
    }

}
