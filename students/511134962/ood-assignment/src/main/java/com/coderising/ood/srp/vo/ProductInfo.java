/**********************************************************************************************************************
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.                                       *
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.                        *
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.                                                   *
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.                     *
 * Vestibulum commodo. Ut rhoncus gravida arcu.                                                                       *
 **********************************************************************************************************************/

package com.coderising.ood.srp.vo;

import java.util.Objects;

public class ProductInfo
{
    private String productID = null;
    private String productDesc = null;

    public ProductInfo() { }

    @Override
    public int hashCode()
    {
        return Objects.hash( getProductID(), getProductDesc() );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( this == o )
        {
            return true;
        }
        if ( !( o instanceof ProductInfo ) )
        {
            return false;
        }
        ProductInfo that = ( ProductInfo ) o;
        return Objects.equals( getProductID(), that.getProductID() ) && Objects.equals( getProductDesc(),
                                                                                        that.getProductDesc() );
    }

    public String getProductID()
    {
        return productID;
    }

    public String getProductDesc()
    {

        return productDesc;
    }

    public void setProductDesc( String productDesc )
    {
        this.productDesc = productDesc;
    }

    public void setProductID( String productID )
    {
        this.productID = productID;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder( "ProductInfo{" );
        sb.append( "                productDesc='" ).append( productDesc ).append( '\'' );
        sb.append( ",                 productID='" ).append( productID ).append( '\'' );
        sb.append( '}' );
        return sb.toString();
    }
}