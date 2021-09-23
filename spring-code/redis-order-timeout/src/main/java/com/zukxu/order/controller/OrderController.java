package com.zukxu.order.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zukxu.common.result.Result;
import com.zukxu.orderdemo.entity.OrderInfo;
import com.zukxu.orderdemo.service.IOrderInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单接口
 * </p>
 *
 * @author zukxu
 * CreateTime: 2021/2/25 0025 15:29
 */

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	IOrderInfoService orderInfoService;

	@ApiOperation("查询用户订单列表")
	@ApiImplicitParams({@ApiImplicitParam(value = "订单状态", name = "status", defaultValue = "0"), @ApiImplicitParam(value = "商品名称", name = "name"), @ApiImplicitParam(value = "当前页", name = "current"), @ApiImplicitParam(value = "分页数量", name = "size"),})
	@GetMapping("pageInfo")
	public Result<IPage<OrderInfo>> info(Integer status, String name, Integer current, Integer size) {
		return Result.success(orderInfoService.pageInfo(status, name, current, size));
	}

	@ApiOperation("创建订单列表")
	@PostMapping(value = "addOrderInfo")
	public Result<List<OrderInfo>> addOrderInfo(@RequestBody List<OrderInfo> orderInfos) throws Exception {
		return Result.success(orderInfoService.addOrderInfo(orderInfos));
	}

	@ApiOperation("更新订单列表")
	@PutMapping(value = "updOrderInfo")
	public Result<Void> updOrderInfo(OrderInfo orderInfo) {
		return orderInfoService.updOrderStatus(orderInfo) ? Result.success() : Result.error();
	}

	@ApiOperation("删除订单列表")
	@DeleteMapping(value = "delOrderInfo")
	public Result<Void> delOrderInfo(String orderId) {
		return orderInfoService.removeById(orderId) ? Result.success() : Result.error();
	}
}
